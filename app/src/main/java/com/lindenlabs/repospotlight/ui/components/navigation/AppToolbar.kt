package com.lindenlabs.repospotlight.ui.components.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.ui.components.ThreeItemAppBar
import com.lindenlabs.repospotlight.ui.theme.Dimens
import com.lindenlabs.repospotlight.utils.DestinationAccessHelper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppToolbar(appNavigator: AppNavigator, action: () -> Unit) {
    val navController = appNavigator.navController
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination?.route ?: ""
    val isDetailScreen = DestinationAccessHelper.canShowBackIcon(currentDestination)

    ThreeItemAppBar(
        startAccessory = {
            Box(Modifier.size(Dimens.looseSpacing)) {
                AnimatedVisibility(visible = isDetailScreen) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "MenuButton",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { action() }
                    )
                }
            }
        },
        mainAccessory = {
            if (isDetailScreen) {
                Text(
                    text = ("Repo Spotlight: " + appNavigator.selectedRepo?.name),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            } else {
                Text(
                    text = "Repo Spotlight",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        },
        endAccessory = {

        })
}