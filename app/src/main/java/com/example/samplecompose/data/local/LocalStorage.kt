package com.example.samplecompose.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.samplecompose.domain.model.Keys

class LocalStorage(private val context: Context) {
    private val shared = context.getSharedPreferences("store", MODE_PRIVATE)

    fun putString(key: Keys, value: String?) {
        shared.edit().putString(key.name, value).apply()
    }

    fun getString(keys: Keys): String? {
        return shared.getString(keys.name, null)
    }

}