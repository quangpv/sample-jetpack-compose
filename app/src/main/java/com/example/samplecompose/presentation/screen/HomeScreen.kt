package com.example.samplecompose.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.samplecompose.presentation.extensions.observe
import com.example.samplecompose.domain.model.Scopes
import com.example.samplecompose.domain.usecase.FetchHomeStateCase
import com.example.samplecompose.domain.usecase.RefreshHomeFavoritesCase
import com.example.samplecompose.presentation.extensions.ScopeProvider
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.helper.AppNavigator
import com.example.samplecompose.presentation.widget.ActionBarView
import com.example.samplecompose.presentation.widget.ComponentView
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() = ScopeProvider {
    val launcher = rememberCoroutineScope()
    val appNavigator = use<AppNavigator>(Scopes.App)
    val fetchHomeState = use<FetchHomeStateCase>()
    val refreshFavoritesCase = use<RefreshHomeFavoritesCase>()

    val state = fetchHomeState.result.observe()

    LaunchedEffect(Unit) {
        fetchHomeState()
    }

    Column {
        ActionBarView(text = "Home", onCommand = {
            appNavigator.back()
        })
        Text(text = "Title: ${state.title}", Modifier.clickable {
            launcher.launch { refreshFavoritesCase() }
        })
        ComponentView(item = state.favorites)
    }
}
