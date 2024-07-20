package com.example.samplecompose.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import com.example.samplecompose.domain.model.IChars
import com.example.samplecompose.domain.model.Signal
import com.example.samplecompose.domain.model.Updatable
import com.example.samplecompose.domain.model.signal

@Composable
fun <T> T.observe(): T {
    if (this !is Signal) return this
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

fun Any?.edit(): Updatable? {
    return this as? Updatable
}

val Any?.editor: Updatable?
    get() {
        return this as? Updatable
    }

fun Any?.edit(value: Any?, notify: Boolean = false) {
    edit()?.update(value, notify)
}

inline fun <reified T> Any?.cast(): T? {
    return this as? T
}

inline fun <reified T> mutableValueOf(value: T): CharSequence {
    return object : IChars, Updatable, Signal by signal() {
        var value = value

        override fun update(value: Any?, notify: Boolean) {
            if (value is T) {
                this.value = value
            }
            if (notify) emit()
        }

        override fun toString(): String {
            return value.toString()
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
