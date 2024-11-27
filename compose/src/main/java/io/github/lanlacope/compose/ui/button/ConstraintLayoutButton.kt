package io.github.lanlacope.compose.ui.button

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.solver.widgets.Optimizer

@Composable
inline fun ConstraintLayoutButton(
    noinline onClick: () -> Unit,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    optimizationLevel: Int = Optimizer.OPTIMIZATION_STANDARD,
    animateChangesSpec: AnimationSpec<Float>? = null,
    noinline finishedAnimationListener: (() -> Unit)? = null,
    crossinline content: @Composable ConstraintLayoutScope.() -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .padding(paddingValues = innerPadding),
        optimizationLevel = optimizationLevel,
        animateChangesSpec = animateChangesSpec,
        finishedAnimationListener = finishedAnimationListener,
        content = content
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
inline fun ConstraintLayoutButton(
    modifier: Modifier = Modifier,
    noinline onClick: (() -> Unit)? = null,
    noinline onLongClick: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(),
    optimizationLevel: Int = Optimizer.OPTIMIZATION_STANDARD,
    animateChangesSpec: AnimationSpec<Float>? = null,
    noinline finishedAnimationListener: (() -> Unit)? = null,
    crossinline content: @Composable ConstraintLayoutScope.() -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .combinedClickable(
                onClick = onClick ?: { },
                onLongClick = onLongClick
            )
            .padding(paddingValues = innerPadding),
        optimizationLevel = optimizationLevel,
        animateChangesSpec = animateChangesSpec,
        finishedAnimationListener = finishedAnimationListener,
        content = content
    )
}