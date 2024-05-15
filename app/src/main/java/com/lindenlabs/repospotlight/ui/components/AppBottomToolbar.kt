package com.lindenlabs.repospotlight.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.navigation.Screen
import timber.log.Timber

internal data class BottomNavigationEntity(
    val label: String,
    val screen: Screen,
    val unselectedIcon: Int,
    val selectedIcon: Int,
    val iconContentDescription: String = label
)


@Composable
internal fun AppBottomToolbar(
    appNavigator: AppNavigator,
    currentTabIndex: Int,
    bottomNavItems: List<BottomNavigationEntity>,
    onTabSelected: (Int) -> Unit
) {
    Column {
        ActiveTabIndicator(currentTabIndex)
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
        ) {

            bottomNavItems.forEach { bottomNavigationItem ->
                BottomNavigationBarItem(
                    bottomNavigationItem = bottomNavigationItem,
                    navHostController = appNavigator.navController,
                    isSelected = bottomNavigationItem.screen == appNavigator.screen,
                    onClick = {
//                        onTabSelected(it.route.ordinal)
                    },
                )
            }
        }
    }
}

@Composable
fun ActiveTabIndicator(tabIndex: Int) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val tabStripWidth = (screenWidth / 2).dp

    val indicatorOffset = when (tabIndex) {
        0 -> 0f
        else -> tabStripWidth.value * tabIndex
    }
    Timber.d("Active Tab Top Indicator screen width Screen $screenWidth Tab $tabIndex Product $indicatorOffset")

    val offsetState = animateDpAsState(targetValue = indicatorOffset.dp)

    Spacer(
        modifier = Modifier
            .offset(offsetState.value, 0.dp)
            .background(color = Color.Black)
            .size(tabStripWidth, 2.dp)
    )
}