package com.lindenlabs.repospotlight.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lindenlabs.repospotlight.ui.theme.Dimens.defaultSpacing

@Composable
fun GenericBox(title: String, modifier: Modifier = Modifier.fillMaxSize()) {
    Box(modifier = modifier) {
        Text(
            text = title, modifier = Modifier
                .padding(top = defaultSpacing)
                .align(Alignment.Center)
        )
    }
}