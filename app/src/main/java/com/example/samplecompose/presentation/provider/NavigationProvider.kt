package com.example.samplecompose.presentation.provider

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.extensions.useRemember
import com.example.samplecompose.presentation.helper.AppNavigator
import org.koin.core.scope.Scope

@Composable
fun Scope.NavigationProvider(function: @Composable (NavHostController) -> Unit) {
    val appNavigator = use<AppNavigator>()
    val navController = rememberNavController()
    useRemember(onForgotten = {
        appNavigator.edit()?.update(null)
    }) {
        appNavigator.edit()?.update(navController)
    }
    function(navController)
}
