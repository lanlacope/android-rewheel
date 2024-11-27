package io.github.lanlacope.compose.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("unused")
@Composable
fun FadeInAnimated(
    visible: Boolean,
    modifier: Modifier = Modifier,
    label: String = "AnimatedVisibility",
    content: @Composable() (AnimatedVisibilityScope.() -> Unit)
) {
    val enter: EnterTransition = fadeIn()
    val exit: ExitTransition = fadeOut()
    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit,
        modifier = modifier,
        label = label,
        content = content
    )
}