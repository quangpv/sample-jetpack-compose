package com.example.samplecompose.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.samplecompose.domain.model.Scopes
import com.example.samplecompose.domain.usecase.FetchLoginStateCase
import com.example.samplecompose.domain.usecase.LoginCase
import com.example.samplecompose.presentation.extensions.ScopeProvider
import com.example.samplecompose.presentation.extensions.observe
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.widget.ActionBarTitleView
import com.example.samplecompose.presentation.widget.InputText
import com.example.samplecompose.presentation.widget.LoadingView
import com.example.samplecompose.presentation.widget.Space
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() = ScopeProvider(Scopes.Login) {
    val launcher = rememberCoroutineScope()
    val state = use<FetchLoginStateCase>().result.observe()
    val login = use<LoginCase>()
    val isLoading = state.loadingState.observe().isLoading

    Column {
        ActionBarTitleView(text = "Login")
        Column(Modifier.padding(16.dp)) {
            Space(16.dp)
            InputText(state.email, "Email")
            Space(16.dp)
            InputText(state.password, "Password")
            Space(24.dp)

            if (isLoading) LoadingView()
            else Button(onClick = {
                launcher.launch { login() }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login")
            }
        }
    }
}
