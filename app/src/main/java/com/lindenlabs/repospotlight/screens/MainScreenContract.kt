package com.lindenlabs.repospotlight.screens

import com.lindenlabs.repospotlight.data.models.RepoModel

object MainScreenContract {

    sealed class ViewState {
        object Loading : ViewState()

        data class Spotlight(val repos: List<RepoModel>) : ViewState() // todo - use view entities

        data class ErrorState(val throwable: Throwable) : ViewState()

    }
}