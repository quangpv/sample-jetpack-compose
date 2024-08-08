package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.StateRepo
import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.presentation.extensions.mutableCharStateOf
import com.example.samplecompose.domain.model.IHome
import com.example.samplecompose.domain.model.Signal
import com.example.samplecompose.domain.model.signal
import kotlinx.coroutines.delay


class FetchHomeStateCase(private val stateRepo: StateRepo) {
    private val state = object : IHome, Signal by signal() {
        override val title: CharSequence = mutableCharStateOf("Loading Title...")
        override val favorites: CharSequence = mutableCharStateOf("Loading Favorites...")
    }.also { stateRepo.setState(it) }

    val result: IHome get() = state

    suspend operator fun invoke() {
        result.title.edit()?.update("Home [click here to refresh favorites]")
        result.favorites.edit()?.update("Home favorites")
        delay(2000)
        state.emit()
    }
}