package com.example.samplecompose.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.samplecompose.presentation.extensions.observe
import com.example.samplecompose.domain.model.Command
import com.example.samplecompose.domain.model.ILoginAgent
import com.example.samplecompose.domain.model.Scopes
import com.example.samplecompose.domain.usecase.EditHomeAgentCase
import com.example.samplecompose.domain.usecase.FetchLoginStateCase
import com.example.samplecompose.domain.usecase.LoginCase
import com.example.samplecompose.presentation.extensions.ScopeProvider
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.widget.ActionBarView
import com.example.samplecompose.presentation.widget.ComponentView
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() = ScopeProvider(Scopes.Login) {
    val launcher = rememberCoroutineScope()
    val state = use<FetchLoginStateCase>().result.observe()
    val login = use<LoginCase>()
    val editAgent = use<EditHomeAgentCase>()

    val command: (Command) -> Unit = {
        if (it is Command.Click && it.item is ILoginAgent) editAgent(it.item)
    }

    Column {
        ActionBarView(text="Login"){

        }
        ComponentView(state.logo)
        state.agents.forEach {
            ComponentView(it, command)
        }
        Button(onClick = {
            launcher.launch { login() }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login")
        }
    }
}