package com.lindenlabs.repospotlight.navigation

import androidx.navigation.NavHostController
import com.lindenlabs.repospotlight.data.models.RepoModel

class AppNavigator (val navController: NavHostController) {
    var selectedRepo: RepoModel? = null

    fun navigate(screen: Screen.Detail) {
        this.selectedRepo = screen.repoModel
        navController.navigate(screen.route) {
            launchSingleTop = false
        }
    }

    fun goBack(){
        navController.navigate(Screen.Home.route) // todo - investigate why navController.navigateUp() isn't working
    }
}