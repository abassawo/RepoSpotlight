package com.lindenlabs.repospotlight

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.ui.components.AppToolbar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(appNavigator: AppNavigator) {
    Scaffold (
        contentWindowInsets = WindowInsets(0, 0, 0, 16),
        topBar = {
            AppToolbar {

            }
        },
        bottomBar = {

        }
    ) {

    }
}