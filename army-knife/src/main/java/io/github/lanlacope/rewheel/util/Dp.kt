package io.github.lanlacope.rewheel.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}

@Composable
fun Dp.toSp(): TextUnit {
    val density = LocalDensity.current
    return with(density) { this@toSp.toSp() }
}