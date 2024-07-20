package com.example.samplecompose.presentation

import com.example.samplecompose.presentation.helper.ScopedCoroutineScope
import com.example.samplecompose.presentation.helper.AppNavigator
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module


val presentationModule = module {
    scope<Any> {
        scoped { AppNavigator() }
        scoped<CoroutineScope> { ScopedCoroutineScope() }
    }
}