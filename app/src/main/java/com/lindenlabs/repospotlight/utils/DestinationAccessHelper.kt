package com.lindenlabs.repospotlight.utils

object DestinationAccessHelper {
    fun canShowBackIcon(route: String): Boolean {
        return route.contains(
                    "detail"
                ) == true
    }
}