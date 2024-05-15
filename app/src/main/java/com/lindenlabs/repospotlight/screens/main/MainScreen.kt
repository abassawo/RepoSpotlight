package com.lindenlabs.repospotlight.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lindenlabs.repospotlight.ui.components.GenericBox
import com.lindenlabs.repospotlight.ui.components.RepoCard

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val viewState = viewModel.viewState.collectAsState().value

    when (viewState) {
        is MainScreenContract.ViewState.ErrorState -> GenericBox(title = "Error")
        MainScreenContract.ViewState.Loading -> GenericBox(title = "Loading")
        is MainScreenContract.ViewState.Spotlight -> SpotlightReposScreen(viewState.viewEntities)
    }
}

@Composable
fun SpotlightReposScreen(viewEntities: List<SpotlightRepoViewEntity>) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(viewEntities) { viewEntity ->
            RepoCard(repoModel = viewEntity.repoModel)
        }
    }
}