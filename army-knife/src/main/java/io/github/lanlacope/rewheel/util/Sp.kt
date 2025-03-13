package io.github.lanlacope.rewheel.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextUnit.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}

@Composable
fun TextUnit.toDp(): Dp {
    val density = LocalDensity.current
    return with(density) { this@toDp.toDp() }
}