package com.lindenlabs.repospotlight.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppToolbar(action: () -> Unit) {
    ThreeItemAppBar(
        startAccessory = {
            IconButton(
                onClick = {
                    action()
                }
            ) {
                Icon(
                    // internal hamburger menu
                    Icons.Default.Menu,
                    contentDescription = "MenuButton",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        mainAccessory = {
            Text(text = "Repo Spotlight", color = Color.White)
        },
        endAccessory = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }


        }
    )
}
