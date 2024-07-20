package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.AppRepo
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.presentation.extensions.mutableCharStateOf
import com.example.samplecompose.domain.model.IApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppStateCase(
    private val appRepo: AppRepo,
    private val scope: CoroutineScope,
) : IApp {
    override val currentScreen: CharSequence = mutableCharStateOf(appRepo.getCurrentScreen().name)

    init {
        scope.launch {
            appRepo.currenScreenFlow.collect {
                currentScreen.edit()?.update(it.name, true)
            }
        }
    }
}