package com.lindenlabs.repospotlight.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.navigation.Screen
import com.lindenlabs.repospotlight.ui.components.GenericBox
import com.lindenlabs.repospotlight.ui.components.LoadingView
import com.lindenlabs.repospotlight.ui.components.spotlight.RepoCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun MainScreen(appNavigator: AppNavigator, modifier: Modifier) {
    val viewModel = hiltViewModel<MainViewModel>()
    val viewState = viewModel.viewState.collectAsState().value

    val viewEvent = viewModel.viewEvent.collectAsState().value

    when (viewEvent) {
        is MainScreenContract.ViewEvent.NavigateToDetailScreen -> appNavigator.navigate(
            Screen.Detail(
                viewEvent.repoModel
            )
        )

        null -> Unit
    }

    when (viewState) {
        is MainScreenContract.ViewState.ErrorState -> GenericBox(title = "Error")
        MainScreenContract.ViewState.Loading -> LoadingView()
        MainScreenContract.ViewState.Empty -> GenericBox(title = "Nothing to see here!")
        is MainScreenContract.ViewState.Spotlight -> SpotlightReposScreen(
            viewModel,
            viewState.viewEntities,
            modifier
        )
    }

}

@Composable
fun SpotlightReposScreen(
    viewModel: MainViewModel,
    viewEntities: List<SpotlightRepoViewEntity>,
    modifier: Modifier
) {
    val scrollState = rememberLazyListState()
    Box(modifier) {
        LazyColumn(Modifier.fillMaxSize(), state = scrollState) {
            items(viewEntities) { viewEntity ->
                RepoCard(
                    repoModel = viewEntity.repoModel
                ) { viewEntity.clickAction(viewEntity.repoModel) }

            }
        }
        if (scrollState.canScrollForward.not()) {
            viewModel.fetchTopRepos()
        }
    }
}