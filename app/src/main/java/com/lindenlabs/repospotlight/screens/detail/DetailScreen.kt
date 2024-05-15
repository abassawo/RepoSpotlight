package com.lindenlabs.repospotlight.screens.detail

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.lindenlabs.repospotlight.data.models.RepoModel
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.ui.components.GenericBox
import com.lindenlabs.repospotlight.ui.components.LoadingView
import com.lindenlabs.repospotlight.ui.components.spotlight.ContributorsCard
import com.lindenlabs.repospotlight.ui.theme.Dimens

@Composable
fun DetailScreen(appNavigator: AppNavigator, modifier: Modifier) {
    val selectedRepo = appNavigator.selectedRepo
    selectedRepo?.let { repo ->
        val context = LocalContext.current
        val viewModel = hiltViewModel<DetailViewModel>()
        val viewState = viewModel.viewState.collectAsState().value

        val viewEvent = viewModel.viewEvent.collectAsState().value
        when (viewEvent) {
            is DetailContract.ViewEvent.ShowCustomChromeTab -> context.openChromeCustomTab(repo)
            null -> Unit
        }


        LaunchedEffect(Unit) {
            viewModel.init(repo)
        }
        Column(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                when (viewState) {
                    is DetailContract.ViewState.Content -> {
                        ContributorsCard(contributors = viewState.contributors)
                    }

                    DetailContract.ViewState.Empty -> GenericBox(title = "Nothing to see here!")
                    DetailContract.ViewState.Failure -> GenericBox(title = "An unexpected error occurred")
                    DetailContract.ViewState.Loading -> LoadingView()
                }
            }
            Button(
                onClick = {
                    viewModel.handleInteraction(DetailContract.Interaction.ViewRepoClicked(repo))
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize()
                    .padding(horizontal = Dimens.defaultSpacing)
                    .padding(bottom = Dimens.defaultSpacing)
            ) { // Add padding to the button for better spacing
                Text("View Repo")
            }
        }
    }
}

fun Context.openChromeCustomTab(repoModel: RepoModel) =
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()
        .launchUrl(this, Uri.parse(repoModel.htmlUrl))
