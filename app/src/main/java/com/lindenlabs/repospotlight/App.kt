package com.lindenlabs.repospotlight

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.navigation.Screen
import com.lindenlabs.repospotlight.screens.TopReposListScreen
import com.lindenlabs.repospotlight.ui.components.AppToolbar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(appNavigator: AppNavigator) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 16),
        topBar = {
            AppToolbar {

            }
        },
        bottomBar = {

        }
    ) { paddingValues ->
        NavHost(
            navController = appNavigator.navController,
            startDestination = Screen.Home.route,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {
            composable(route = Screen.Home.route) {
                TopReposListScreen()
            }
        }
    }
}