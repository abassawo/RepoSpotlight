package com.lindenlabs.repospotlight.navigation

import androidx.navigation.NavHostController
import com.lindenlabs.repospotlight.ui.components.BottomNavigationEntity
import javax.inject.Inject

interface AppNavigation {
    fun navigate(screen: Screen)
}

class AppNavigator @Inject constructor(val navController: NavHostController) {
    fun isScreenSet(bottomNavigationItem: BottomNavigationEntity): Boolean {
        return navController.currentDestination?.route == bottomNavigationItem.screen.route
    }
}