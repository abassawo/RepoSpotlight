package com.lindenlabs.repospotlight.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
internal fun RowScope.BottomNavigationBarItem(
    bottomNavigationItem: BottomNavigationEntity,
    navHostController: NavHostController,
    isSelected: Boolean,
    onClick: (bottomNavigationItem: BottomNavigationEntity) -> Unit,
) {
    NavigationBarItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        colors = navigationBarItemColors(),
        label = {
            BottomNavLabel(text = bottomNavigationItem.label)
        },
        icon = {
            with(bottomNavigationItem.toDrawableState(isSelected)) {
                Image(
                    painter = painterResource(this),
                    contentDescription = bottomNavigationItem.iconContentDescription,
                )
            }
        },
        selected = isSelected,
        onClick = {
            onClick(bottomNavigationItem)
            val bottomNavItem = bottomNavigationItem.screen
            navHostController.navigate(bottomNavItem.route) {
                popUpTo(navHostController.graph.id)
                launchSingleTop = true
            }
        },
    )
}

private fun BottomNavigationEntity.toDrawableState(isSelected: Boolean) =
    if (isSelected) {
        selectedIcon
    } else {
        unselectedIcon
    }


@Composable
fun navigationBarItemColors() = NavigationBarItemDefaults.colors(
    indicatorColor = MaterialTheme.colorScheme.background,
)

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(80.dp)
                .wrapContentHeight()
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            content = content
        )
    }
}

@Composable
fun BottomNavLabel(text: String) {
    Text(
        text,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 11.sp,
            color = Color.Blue,
            fontWeight = FontWeight(400),
            lineHeight = 14.sp
        )
    )
}
