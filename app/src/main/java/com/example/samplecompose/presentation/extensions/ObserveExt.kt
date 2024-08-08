package com.example.samplecompose.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.example.samplecompose.domain.model.HasIsLoading
import com.example.samplecompose.domain.model.HasIsValid
import com.example.samplecompose.domain.model.HasValidable
import com.example.samplecompose.domain.model.IChars
import com.example.samplecompose.domain.model.NoValidable
import com.example.samplecompose.domain.model.Signal
import com.example.samplecompose.domain.model.Subscriber
import com.example.samplecompose.domain.model.Updatable
import com.example.samplecompose.domain.model.Validable
import com.example.samplecompose.domain.model.ValidableDelegate
import com.example.samplecompose.domain.model.signal

@Composable
fun <T> T.observe(): T {
    if (this !is Subscriber) return this
    val result = remember { mutableStateOf(this, neverEqualPolicy()) }
    val self = this

    remember(this) {
        object : RememberObserver {
            private var closable: AutoCloseable? = null

            override fun onAbandoned() {
                closable?.close()
                closable = null
            }

            override fun onForgotten() {
                closable?.close()
                closable = null
            }

            override fun onRemembered() {
                closable?.close()
                closable = subscribe { result.value = self }
            }
        }
    }
    return result.value
}

interface ITextInput : (TextFieldValue) -> Unit {
    val value: TextFieldValue
    val rawValue: CharSequence
}

@Composable
fun CharSequence.observeInput(): ITextInput {
    val self = this
    val result = remember {
        var state: MutableState<ITextInput>? = null
        val input = object : ITextInput, Updatable {
            override var value: TextFieldValue = TextFieldValue(self.toString())
            override val rawValue: CharSequence get() = self
            override fun invoke(p1: TextFieldValue) {
                value = p1
                self.edit()?.update(p1.text)
                state?.value = this
            }

            override fun update(value: Any?, notify: Boolean) {
                if (value is CharSequence) {
                    this.value = TextFieldValue(value.toString())
                    state?.value = this
                }
            }
        }
        state = mutableStateOf(input as ITextInput, neverEqualPolicy())
        state
    }

    if (this is Subscriber) remember(this) {
        object : RememberObserver {
            private var closable: AutoCloseable? = null

            override fun onAbandoned() {
                closable?.close()
                closable = null
            }

            override fun onForgotten() {
                closable?.close()
                closable = null
            }

            override fun onRemembered() {
                closable?.close()
                closable = subscribe {
                    result.value.edit()?.update(self.toString())
                }
            }
        }
    }
    return result.value
}

fun Any?.edit(): Updatable? {
    return this as? Updatable
}

inline fun <reified T> Any?.cast(): T? {
    return this as? T
}

fun mutableLoadingStateOf(def: Boolean): HasIsLoading {
    return object : HasIsLoading, Updatable, Signal by signal() {
        override var isLoading: Boolean = def

        override fun update(value: Any?, notify: Boolean) {
            if (value is Boolean) isLoading = value
            emit()
        }
    }
}

fun mutableCharStateOf(def: CharSequence): CharSequence {
    return object : IChars, Updatable, Signal by signal() {
        var value = def

        override fun update(value: Any?, notify: Boolean) {
            this.value = value?.toString().orEmpty()
            if (notify) emit()
        }

        override fun toString(): String {
            return value.toString()
        }
    }
}

fun textInputStateOf(
    def: CharSequence,
    validable: Validable = NoValidable
): CharSequence {
    val validableDelegate = ValidableDelegate(validable).apply { update(def) }

    return object : IChars, Updatable, Signal by signal(),
        HasIsValid by validableDelegate,
        Validable {

        var value = def

        override fun update(value: Any?, notify: Boolean) {
            this.value = value?.toString().orEmpty()

            if (value is HasValidable) validableDelegate.delegate = validableDelegate.delegate
            else validableDelegate.update(value, notify)

            if (notify) emit()
        }

        override fun validate(): Boolean {
            return validableDelegate.validate().also { emit() }
        }

        override fun toString(): String {
            return value.toString()
        }
    }
}
