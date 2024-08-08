@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.samplecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.samplecompose.domain.model.Scopes
import com.example.samplecompose.domain.model.Screen
import com.example.samplecompose.presentation.helper.AppPopup
import com.example.samplecompose.presentation.extensions.ScopeProvider
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.extensions.useRemember
import com.example.samplecompose.presentation.helper.AppNavigator
import com.example.samplecompose.presentation.provider.NavigationProvider
import com.example.samplecompose.presentation.provider.PopupProvider
import com.example.samplecompose.presentation.screen.HomeScreen
import com.example.samplecompose.presentation.screen.LoginScreen
import com.example.samplecompose.presentation.theme.SampleComposeTheme
import org.koin.compose.KoinContext
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinContext {
                SampleComposeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White
                    ) {
                        ScopeProvider(Scopes.App) {
                            PopupProvider {
                                NavigationProvider {
                                    NavHost(
                                        navController = it, startDestination = Screen.Login.name
                                    ) {
                                        composable(Screen.Login.name) {
                                            LoginScreen()
                                        }
                                        composable(Screen.Home.name) {
                                            HomeScreen()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}