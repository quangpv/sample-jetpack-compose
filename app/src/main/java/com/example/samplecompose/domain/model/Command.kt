package com.example.samplecompose.domain.model

sealed interface Command {
    class Click(val item: Any) : Command
    object ActionBarBack : Command
}