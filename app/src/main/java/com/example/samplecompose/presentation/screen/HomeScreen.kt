package com.example.samplecompose.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.samplecompose.domain.model.Scopes
import com.example.samplecompose.domain.usecase.FetchHomeStateCase
import com.example.samplecompose.domain.usecase.RefreshHomeFavoritesCase
import com.example.samplecompose.presentation.extensions.ScopeProvider
import com.example.samplecompose.presentation.extensions.observe
import com.example.samplecompose.presentation.extensions.use
import com.example.samplecompose.presentation.helper.AppNavigator
import com.example.samplecompose.presentation.widget.ActionBarBackAndTitleView
import com.example.samplecompose.presentation.widget.ComponentView
import com.example.samplecompose.presentation.widget.Space
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
        ActionBarBackAndTitleView(text = "Home", onCommand = {
            appNavigator.back()
        })
        Column {
            Space(height = 16.dp)
            Text(text = "Title: ${state.title}", Modifier.clickable {
                launcher.launch { refreshFavoritesCase() }
            })
            Space(height = 16.dp)
            ComponentView(item = state.favorites)
        }
    }
}
