package com.example.samplecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.domain.model.Scopes
import com.example.samplecompose.domain.model.Screen
import com.example.samplecompose.presentation.extensions.ScopeProvider
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.extensions.useScope
import com.example.samplecompose.presentation.helper.AppNavigator
import com.example.samplecompose.presentation.theme.SampleComposeTheme
import com.example.samplecompose.presentation.screen.HomeScreen
import com.example.samplecompose.presentation.screen.LoginScreen
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
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    ScopeProvider(Scopes.App) {
        NavigationProvider {
            NavHost(navController = it, startDestination = "login") {
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

@Composable
fun Scope.NavigationProvider(function: @Composable (NavHostController) -> Unit) {
    val appNavigator = use<AppNavigator>()
    val navController = rememberNavController()
    remember {
        object : RememberObserver {
            override fun onRemembered() {
                appNavigator.edit()?.update(navController)
            }

            override fun onAbandoned() {
                appNavigator.edit()?.update(navController)
            }

            override fun onForgotten() {
                appNavigator.edit()?.update(null)
            }
        }
    }
    function(navController)
}
