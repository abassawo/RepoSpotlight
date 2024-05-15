package com.lindenlabs.repospotlight.screens.detail

import com.lindenlabs.repospotlight.data.models.Contributor
import com.lindenlabs.repospotlight.data.models.RepoModel

object DetailContract {

    sealed class ViewState {
        object Empty : ViewState() // This shouldn't occur

        data class Content(val contributors: List<Contributor>) : ViewState()

        object Failure : ViewState()

        object Loading : ViewState()
    }

    sealed class Interaction {
        data class ViewRepoClicked(val repoModel: RepoModel) : Interaction()
    }

    sealed class ViewEvent {
        data class ShowCustomChromeTab(val repoModel: RepoModel) : ViewEvent()
    }
}