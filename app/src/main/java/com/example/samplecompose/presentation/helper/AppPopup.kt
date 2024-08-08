package com.example.samplecompose.presentation.helper

import androidx.compose.material3.SnackbarHostState
import com.example.samplecompose.domain.model.Updatable

class AppPopup : Updatable {

    private var snackbarHostState: SnackbarHostState? = null

    suspend fun showIfError(function: suspend () -> Unit) {
        try {
            function()
        } catch (e: Throwable) {
            snackbarHostState?.showSnackbar(e.message ?: "")
        }
    }

    override fun update(value: Any?, notify: Boolean) {
        snackbarHostState = if (value is SnackbarHostState) value else null
    }

    suspend fun showError(s: String) {
        snackbarHostState?.showSnackbar(s)
    }

}