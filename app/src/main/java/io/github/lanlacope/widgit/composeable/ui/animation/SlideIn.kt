package io.github.lanlacope.widgit.composeable.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("unused")
@Composable
fun SlideInAnimated(
    visible: Boolean,
    modifier: Modifier = Modifier,
    label: String = "AnimatedVisibility",
    content: @Composable() (AnimatedVisibilityScope.() -> Unit)
) {
    val enter: EnterTransition = slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth }
    ) + fadeIn()

    val exit: ExitTransition = slideOutHorizontally(
        targetOffsetX = { fullWidth -> fullWidth }
    ) + fadeOut()

    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit,
        modifier = modifier,
        label = label,
        content = content
    )
}