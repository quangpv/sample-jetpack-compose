package com.example.samplecompose.domain.model

interface Updatable {
    fun update(value: Any?, notify: Boolean = false)
}