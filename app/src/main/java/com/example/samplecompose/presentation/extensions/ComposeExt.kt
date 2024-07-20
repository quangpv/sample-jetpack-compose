package com.example.samplecompose.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember
import org.koin.compose.getKoin
import org.koin.core.scope.Scope
import org.koin.mp.KoinPlatform


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
