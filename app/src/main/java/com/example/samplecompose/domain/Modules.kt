package com.example.samplecompose.domain

import com.example.samplecompose.domain.extension.getBy
import com.example.samplecompose.domain.model.Scopes
import com.example.samplecompose.domain.usecase.AppStateCase
import com.example.samplecompose.domain.usecase.EditHomeAgentCase
import com.example.samplecompose.domain.usecase.FetchHomeStateCase
import com.example.samplecompose.domain.usecase.FetchLoginStateCase
import com.example.samplecompose.domain.usecase.LoginCase
import com.example.samplecompose.domain.usecase.RefreshHomeFavoritesCase
import org.koin.dsl.module


val domainModule = module {
    scope<Any> {
        factory { AppStateCase(get(), get()) }
        factory { EditHomeAgentCase() }
        factory { FetchHomeStateCase(get()) }
        factory { FetchLoginStateCase(get()) }
        factory { LoginCase(get(), getBy(Scopes.App)) }
        factory { RefreshHomeFavoritesCase(get()) }
    }
}