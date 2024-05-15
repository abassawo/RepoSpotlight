package com.lindenlabs.repospotlight.navigation

import com.lindenlabs.repospotlight.data.models.RepoModel

sealed class Screen(open val route: String) {
    object Home : Screen(HOME_ROUTE)

    data class Detail(val repoModel: RepoModel) : Screen("$DETAIL_ROUTE?id=${repoModel.id}")

    companion object {
        private const val HOME_ROUTE = "home_screen"
        private const val DETAIL_ROUTE = "detail_screen"

    }
}