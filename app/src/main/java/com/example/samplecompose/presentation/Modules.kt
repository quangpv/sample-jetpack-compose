package com.example.samplecompose.presentation

import com.example.samplecompose.presentation.helper.ActivityRetriever
import com.example.samplecompose.presentation.helper.AppKeyboard
import com.example.samplecompose.presentation.helper.AppPopup
import com.example.samplecompose.presentation.helper.ScopedCoroutineScope
import com.example.samplecompose.presentation.helper.AppNavigator
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module


val presentationModule = module {
    single(createdAtStart = true) { ActivityRetriever(get()) }
    single { AppKeyboard(get()) }
    scope<Any> {
        scoped { AppNavigator() }
        scoped { AppPopup() }
        scoped<CoroutineScope> { ScopedCoroutineScope() }
    }
}