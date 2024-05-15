package com.lindenlabs.repospotlight.navigation

sealed class Screen(open val route: String) {
    object Home : Screen(HOME_ROUTE)

//    data class Detail(val id: String) : Screen("$DETAIL_ROUTE?id=$id")

    companion object {
        private const val HOME_ROUTE = "home_screen"
        private const val DETAIL_ROUTE = "detail_screen"

    }
}