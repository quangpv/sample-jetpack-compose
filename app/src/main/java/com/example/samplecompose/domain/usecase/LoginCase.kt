package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.StateRepo
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.domain.model.ILogin
import com.example.samplecompose.presentation.helper.AppNavigator
import kotlinx.coroutines.delay

class LoginCase(
    private val stateRepo: StateRepo,
    private val appNavigator: AppNavigator
) {
    suspend operator fun invoke() {
        val state = stateRepo.getState<ILogin>() ?: return
        state.logo.edit("Loading", true)
        delay(2000)
        appNavigator.navigateHome()
    }
}