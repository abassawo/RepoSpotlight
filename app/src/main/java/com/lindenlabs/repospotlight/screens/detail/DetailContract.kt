package com.lindenlabs.repospotlight.screens.detail

import com.lindenlabs.repospotlight.data.models.Contributor

object DetailContract {

    sealed class ViewState {
        object Empty : ViewState() // This shouldn't occur

        data class Content(val contributors: List<Contributor>) : ViewState()

        object Failure : ViewState()

        object Loading : ViewState()
    }
}