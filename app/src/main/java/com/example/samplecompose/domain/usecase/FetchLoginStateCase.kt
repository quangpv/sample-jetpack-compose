package com.example.samplecompose.domain.usecase

import com.example.samplecompose.data.repo.StateRepo
import com.example.samplecompose.presentation.extensions.mutableCharStateOf
import com.example.samplecompose.domain.model.ILogin
import com.example.samplecompose.domain.model.ILoginAgent
import com.example.samplecompose.domain.model.Signal
import com.example.samplecompose.domain.model.Updatable
import com.example.samplecompose.domain.model.signal

class FetchLoginStateCase(
    private val stateRepo: StateRepo
) {
    val result: ILogin = object : ILogin, Signal by signal() {
        override val logo: CharSequence = mutableCharStateOf("Test")
        override val agents: List<ILoginAgent> = listOf(
            createAgent("Facebook"),
            createAgent("Google"),
            createAgent("Telegram"),
        )
    }.also { stateRepo.setState(it) }

    private fun createAgent(name: String): ILoginAgent {
        return object : ILoginAgent, Signal by signal(), Updatable {
            override var name: CharSequence = name

            override fun update(value: Any?, notify: Boolean) {
                this.name = value.toString()
                if (notify) emit()
            }
        }
    }
}