package com.example.samplecompose.data.repo

import com.example.samplecompose.data.helper.SaveStateHandler

class StateRepo(private val saveStateHandler: SaveStateHandler) {
    private var mState: Any? = null

    fun setState(state: Any) {
        mState = state
        saveStateHandler.save(state)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getState(): T? {
        return mState as? T
    }

}