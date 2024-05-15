package com.lindenlabs.repospotlight.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lindenlabs.repospotlight.ui.components.GenericBox
import com.lindenlabs.repospotlight.ui.components.pullrefresh.PullRefreshIndicator
import com.lindenlabs.repospotlight.ui.components.pullrefresh.pullRefresh
import com.lindenlabs.repospotlight.ui.components.pullrefresh.rememberPullRefreshState
import com.lindenlabs.repospotlight.ui.components.spotlight.RepoCard
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val viewState = viewModel.viewState.collectAsState().value

    when (viewState) {
        is MainScreenContract.ViewState.ErrorState -> GenericBox(title = "Error")
        MainScreenContract.ViewState.Loading -> GenericBox(title = "Loading")
        MainScreenContract.ViewState.Empty ->  GenericBox(title = "Nothing to see here!")
        is MainScreenContract.ViewState.Spotlight -> SpotlightReposScreen(viewModel, viewState.viewEntities)
    }
}

@Composable
fun SpotlightReposScreen(viewModel: MainViewModel, viewEntities: List<SpotlightRepoViewEntity>) {
    val refreshScope = rememberCoroutineScope()
    val refreshing = remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing.value = true
        viewModel.fetchTopRepos()
        refreshing.value = false
    }
    val scrollState = rememberLazyListState()
    val state = rememberPullRefreshState(refreshing.value, ::refresh)
    Box(Modifier.pullRefresh(state)) {
        LazyColumn(Modifier.fillMaxSize(), state = scrollState) {
            items(viewEntities) { viewEntity ->
                RepoCard(repoModel = viewEntity.repoModel)
            }
        }
        if(scrollState.canScrollForward.not()) {
            viewModel.fetchTopRepos()
        }
        PullRefreshIndicator(refreshing.value, state, Modifier.align(Alignment.TopCenter))
    }
}