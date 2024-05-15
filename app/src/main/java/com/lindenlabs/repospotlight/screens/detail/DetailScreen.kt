package com.lindenlabs.repospotlight.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.ui.components.spotlight.ContributorsCard

@Composable
fun DetailScreen(appNavigator: AppNavigator, modifier: Modifier) {
    val selectedRepo = appNavigator.selectedRepo
    selectedRepo?.let {
        val viewModel = hiltViewModel<DetailViewModel>()
        val viewState = viewModel.viewState.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.init(it)
        }
        Box(modifier = modifier) {
            when (viewState) {
                is DetailContract.ViewState.Content -> {
                    ContributorsCard(contributors = viewState.contributors)
                }

                DetailContract.ViewState.Empty -> Unit
                DetailContract.ViewState.Failure -> Unit
                DetailContract.ViewState.Loading -> Unit
            }
        }
    }
}