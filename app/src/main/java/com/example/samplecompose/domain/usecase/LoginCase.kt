package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.LoginRepo
import com.example.samplecompose.data.repo.StateRepo
import com.example.samplecompose.domain.model.HasValidable
import com.example.samplecompose.domain.model.ILogin
import com.example.samplecompose.domain.model.Validable
import com.example.samplecompose.presentation.extensions.cast
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.presentation.helper.AppNavigator
import com.example.samplecompose.presentation.helper.AppPopup
import com.example.samplecompose.presentation.helper.AppKeyboard
import kotlinx.coroutines.delay

class LoginCase(
    private val appNavigator: AppNavigator,
    private val appPopup: AppPopup,
    private val stateRepo: StateRepo,
    private val appKeyboard: AppKeyboard,
    private val loginRepo: LoginRepo
) {
    suspend operator fun invoke() {
        val state = stateRepo.getState<ILogin>() ?: return
        appKeyboard.hide()

        if (state.email.cast<Validable>()?.validate() == false)
            return appPopup.showError("Email should not be blank")
        if (state.password.cast<Validable>()?.validate() == false)
            return appPopup.showError("Password should not be blank")

        state.loadingState.edit()?.update(true)

        loginRepo.login(state.email, state.password)
        loginRepo.saveEmail(state.email)

        appPopup.showIfError {
            delay(2000)
            appNavigator.navigateHome()
        }
    }
}

