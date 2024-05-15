package com.lindenlabs.repospotlight

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.navigation.Screen
import com.lindenlabs.repospotlight.screens.detail.DetailScreen
import com.lindenlabs.repospotlight.screens.main.MainScreen
import com.lindenlabs.repospotlight.ui.components.navigation.AppToolbar
import com.lindenlabs.repospotlight.utils.DestinationAccessHelper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(appNavigator: AppNavigator) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 16),
        topBar = {
            AppToolbar(appNavigator) {
                appNavigator.goBack()
            }
        },
        bottomBar = {

        }
    ) { paddingValues ->
        NavHost(
            navController = appNavigator.navController,
            startDestination = Screen.Home.route
        ) {
            composable(route = Screen.Home.route) {
                MainScreen(appNavigator, Modifier.padding(paddingValues))
            }
            composable(
                route = "detail_screen?id={repoId}",
                arguments = listOf(navArgument("repoId") {
                    defaultValue = ""
                    type = NavType.StringType
                })
            ) {
                DetailScreen(appNavigator,  Modifier.padding(paddingValues))
            }
        }
    }
}