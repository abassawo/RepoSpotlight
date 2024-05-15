package com.lindenlabs.repospotlight.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimens {
    val tightestSpacing = 4.dp
    val tighterSpacing = 8.dp
    val tightSpacing = 12.dp
    val defaultSpacing = 16.dp
    val looseSpacing = 24.dp
    val looserSpacing = 32.dp
    val loosestSpacing = 48.dp
}

data class Spacing(
    val none: Dp = 0.dp,
    val tightestSpacing: Dp = Dimens.tightestSpacing,
    val tighterSpacing: Dp = Dimens.tighterSpacing,
    val tightSpacing: Dp = Dimens.tightSpacing,
    val defaultSpacing: Dp = Dimens.defaultSpacing,
    val looseSpacing: Dp = Dimens.looseSpacing,
    val looserSpacing: Dp = Dimens.looserSpacing,
    val loosestSpacing: Dp = Dimens.loosestSpacing
)

val LocalSpacing = compositionLocalOf { Spacing() }