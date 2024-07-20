package com.example.samplecompose.data

import com.example.samplecompose.data.helper.SaveStateHandler
import com.example.samplecompose.data.repo.AppRepo
import com.example.samplecompose.data.repo.StateRepo
import org.koin.dsl.module


val dataModule = module {
    single { SaveStateHandler() }

    scope<Any> {
        scoped { AppRepo() }
        scoped { StateRepo(get()) }
    }
}