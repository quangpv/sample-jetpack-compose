package com.example.samplecompose

import android.app.Application
import com.example.samplecompose.data.dataModule
import com.example.samplecompose.domain.domainModule
import com.example.samplecompose.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger()
            modules(presentationModule, dataModule, domainModule)
        }
    }
}
