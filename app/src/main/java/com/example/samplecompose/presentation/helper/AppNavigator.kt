package com.example.samplecompose.presentation.helper

import androidx.navigation.NavHostController
import com.example.samplecompose.domain.model.Screen
import com.example.samplecompose.domain.model.Updatable

class AppNavigator : Updatable {
    private var navHost: NavHostController? = null

    fun back() {
        navHost?.popBackStack()
    }

    override fun update(value: Any?, notify: Boolean) {
        if (value is NavHostController) {
            this.navHost = value
        }
    }

    fun navigateHome() {
        navHost?.navigate(Screen.Home.name)
    }
}