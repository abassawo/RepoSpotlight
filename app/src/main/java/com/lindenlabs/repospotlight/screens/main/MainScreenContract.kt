package com.lindenlabs.repospotlight.screens.main

object MainScreenContract {

    sealed class ViewState {
        object Loading : ViewState()

        data class Spotlight(val viewEntities: List<SpotlightRepoViewEntity>) : ViewState()

        object Empty : ViewState()

        data class ErrorState(val throwable: Throwable) : ViewState()

    }
}