package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.LoginRepo
import com.example.samplecompose.data.repo.StateRepo
import com.example.samplecompose.domain.model.HasIsLoading
import com.example.samplecompose.domain.model.ILogin
import com.example.samplecompose.domain.model.NotBlankValidable
import com.example.samplecompose.domain.model.Signal
import com.example.samplecompose.domain.model.signal
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.presentation.extensions.mutableCharStateOf
import com.example.samplecompose.presentation.extensions.mutableLoadingStateOf
import com.example.samplecompose.presentation.extensions.textInputStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FetchLoginStateCase(
    private val stateRepo: StateRepo,
    private val loginRepo: LoginRepo,
    private val scope: CoroutineScope
) {

    val result: ILogin = object : ILogin, Signal by signal() {
        override val loadingState: HasIsLoading = mutableLoadingStateOf(false)
        override val email: CharSequence = textInputStateOf("", NotBlankValidable())
        override val password: CharSequence = textInputStateOf("", NotBlankValidable())
    }.also { stateRepo.setState(it) }

    init {
        scope.launch {
            val email = loginRepo.getSavedEmail()
            result.email.edit()?.update(email, true)
        }
    }

}