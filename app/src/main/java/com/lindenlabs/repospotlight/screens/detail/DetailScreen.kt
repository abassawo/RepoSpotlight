package com.lindenlabs.repospotlight.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lindenlabs.repospotlight.navigation.AppNavigator

@Composable
fun DetailScreen(appNavigator: AppNavigator, modifier: Modifier) {
    val selectedRepo = appNavigator.selectedRepo
    selectedRepo?.let {
    }
}