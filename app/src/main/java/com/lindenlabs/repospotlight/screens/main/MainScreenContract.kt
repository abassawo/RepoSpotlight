package com.lindenlabs.repospotlight.screens.main

import com.lindenlabs.repospotlight.data.models.RepoModel

object MainScreenContract {

    sealed class ViewState {
        object Loading : ViewState()

        data class Spotlight(val viewEntities: List<SpotlightRepoViewEntity>) : ViewState()

        object Empty : ViewState()

        data class ErrorState(val throwable: Throwable) : ViewState()

    }

    sealed class Interaction {
        data class SpotlightRepoClicked(val repoModel: RepoModel) : Interaction()
    }

    sealed class ViewEvent {
        data class NavigateToDetailScreen(val repoModel: RepoModel) : ViewEvent()
    }
}