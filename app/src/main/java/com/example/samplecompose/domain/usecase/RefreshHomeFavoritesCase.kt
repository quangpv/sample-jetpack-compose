package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.StateRepo
import com.example.samplecompose.domain.model.IHome
import com.example.samplecompose.presentation.extensions.editor
import java.util.UUID

class RefreshHomeFavoritesCase(private val stateRepo: StateRepo) {
    operator fun invoke() {
        val state = stateRepo.getState<IHome>() ?: return
        state.favorites.editor?.update("New favorites ${UUID.randomUUID()}", true)
    }
}