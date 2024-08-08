package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.StateRepo
import com.example.samplecompose.domain.model.IHome
import com.example.samplecompose.presentation.extensions.edit
import kotlinx.coroutines.delay
import java.util.UUID

class RefreshHomeFavoritesCase(private val stateRepo: StateRepo) {
    suspend operator fun invoke() {
        val state = stateRepo.getState<IHome>() ?: return
        val favorites = state.favorites.edit()
        favorites?.update("Loading...", true)
        delay(1000)
        favorites?.update("New favorites ${UUID.randomUUID()}", true)
    }
}