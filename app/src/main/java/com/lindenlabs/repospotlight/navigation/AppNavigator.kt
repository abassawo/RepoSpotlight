package com.lindenlabs.repospotlight.navigation

import androidx.navigation.NavHostController
import com.lindenlabs.repospotlight.data.models.RepoModel
import com.lindenlabs.repospotlight.ui.components.navigation.BottomNavigationEntity
import javax.inject.Inject

class AppNavigator @Inject constructor(val navController: NavHostController) {
    var selectedRepo: RepoModel? = null
    fun isScreenSet(bottomNavigationItem: BottomNavigationEntity): Boolean {
        return navController.currentDestination?.route == bottomNavigationItem.screen.route
    }

    fun navigate(screen: Screen.Detail) {
        this.selectedRepo = screen.repoModel
        navController.navigate(screen.route) {
            launchSingleTop = true
        }
    }

    fun goBack(){
        navController.navigate(Screen.Home.route) // todo - investigate why navController.navigateUp() isnt working
    }
}