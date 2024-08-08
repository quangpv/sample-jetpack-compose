package com.example.samplecompose.presentation.provider

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.extensions.useRemember
import com.example.samplecompose.presentation.helper.AppPopup
import org.koin.core.scope.Scope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scope.PopupProvider(content: @Composable () -> Unit) {
    val popup = use<AppPopup>()

    val snackbarHostState = remember { SnackbarHostState() }

    useRemember(onForgotten = { popup.update(null) }) {
        popup.update(snackbarHostState)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { contentPadding ->
        contentPadding.calculateTopPadding()
        content()
    }
}