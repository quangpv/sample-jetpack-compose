package com.example.samplecompose.data.repo

import com.example.samplecompose.domain.model.Screen
import kotlinx.coroutines.flow.MutableStateFlow

class AppRepo {
    val currenScreenFlow get() = mCurrent
    private var mCurrent = MutableStateFlow(Screen.Login)

    fun getCurrentScreen(): Screen {
        return mCurrent.value
    }

    fun setCurrentScreen(screen: Screen) {
        mCurrent.tryEmit(screen)
    }
}