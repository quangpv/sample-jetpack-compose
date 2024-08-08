package com.example.samplecompose.data.repo

import com.example.samplecompose.data.local.LocalStorage
import com.example.samplecompose.domain.model.Keys

class LoginRepo(private val localStorage: LocalStorage) {
    fun getSavedEmail(): String {
        return localStorage.getString(Keys.Email) ?: ""
    }

    suspend fun login(email: CharSequence, password: CharSequence) {
    }

    fun saveEmail(email: CharSequence) {
        localStorage.putString(Keys.Email, email.toString())
    }

}

