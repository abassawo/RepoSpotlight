package com.lindenlabs.repospotlight.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreeItemAppBar(
    startAccessory: @Composable () -> Unit,
    mainAccessory: @Composable () -> Unit,
    endAccessory: @Composable () -> Unit
) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            startAccessory()
        },
        title = {
            mainAccessory()
        },
        actions = {
            endAccessory()
        })
}