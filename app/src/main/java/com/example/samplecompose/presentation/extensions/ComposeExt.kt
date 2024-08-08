package com.example.samplecompose.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember


@Composable
fun useEffect(vararg dependencies: Any, function: () -> Unit) {
    remember(*dependencies) {
        object : RememberObserver {
            override fun onRemembered() {
                function()
            }

            override fun onAbandoned() {}
            override fun onForgotten() {}
        }
    }
}


@Composable
fun useRemember(
    vararg dependencies: Any,
    onForgotten: () -> Unit = {},
    onRemembered: () -> Unit = {}
) {
    remember(*dependencies) {
        object : RememberObserver {
            override fun onRemembered() {
                onRemembered()
            }

            override fun onAbandoned() {
                onForgotten()
            }

            override fun onForgotten() {
                onForgotten()
            }
        }
    }
}

@Composable
fun useDispose(vararg dependencies: Any, function: () -> Unit) {
    remember(*dependencies) {
        object : RememberObserver {
            override fun onRemembered() {

            }

            override fun onAbandoned() {}
            override fun onForgotten() {
                function()
            }
        }
    }
}
