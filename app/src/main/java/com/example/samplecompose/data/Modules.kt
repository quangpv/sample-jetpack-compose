package com.example.samplecompose.data

import com.example.samplecompose.data.helper.SaveStateHandler
import com.example.samplecompose.data.local.LocalStorage
import com.example.samplecompose.data.repo.LoginRepo
import com.example.samplecompose.data.repo.StateRepo
import org.koin.dsl.module


val dataModule = module {
    single { SaveStateHandler() }
    single { LocalStorage(get()) }
    scope<Any> {
        scoped { LoginRepo(get()) }
        scoped { StateRepo(get()) }
    }
}