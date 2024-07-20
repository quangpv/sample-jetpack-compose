package com.example.samplecompose.domain.usecase

import com.example.samplecompose.presentation.extensions.edit
import com.example.samplecompose.domain.model.ILoginAgent
import java.util.UUID

class EditHomeAgentCase {
    operator fun invoke(agent: ILoginAgent) {
        agent.edit()?.update("Updated agent ${UUID.randomUUID()}", true)
    }
}