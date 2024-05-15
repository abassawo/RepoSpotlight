package com.lindenlabs.repospotlight.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject

interface AppNavigation {
    fun navigate(screen: Screen)
}

class AppNavigator @Inject constructor(val navController: NavHostController) {
    var screen: Screen = Screen.Splash
}