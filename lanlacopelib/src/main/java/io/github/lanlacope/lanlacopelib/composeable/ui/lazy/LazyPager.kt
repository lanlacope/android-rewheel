package io.github.lanlacope.lanlacopelib.composeable.ui.lazy

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.snapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection

@Suppress("unused")
@Composable
internal fun LazyPager(
    modifier: Modifier,
    state: LazyListState,
    contentPadding: PaddingValues,
    reverseLayout: Boolean = false,
    isVertical: Boolean,
    userScrollEnabled: Boolean,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: LazyListScope.() -> Unit
) {
    val flingBehavior = rememberLazyPagerFlingBehavior(state = state)

    if (isVertical) {
        androidx.compose.foundation.lazy.LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = state,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled,
            content = content
        )
    }
    else {
        androidx.compose.foundation.lazy.LazyRow(
            modifier = modifier.fillMaxSize(),
            state = state,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled,
            content = content
        )
    }
}

@Composable
internal fun rememberLazyPagerFlingBehavior(
    state: LazyListState,
    decayAnimationSpec: DecayAnimationSpec<Float> = remember {
        exponentialDecay(frictionMultiplier = 20.0f)
    },
    snapAnimationSpec: AnimationSpec<Float> = spring(
        stiffness = Spring.StiffnessLow,
        visibilityThreshold = Int.VisibilityThreshold.toFloat()
    ),
): FlingBehavior {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    return remember(state, decayAnimationSpec, snapAnimationSpec, density, layoutDirection) {
        val snapLayoutInfoProvider = SnapLayoutInfoProvider(
            lazyListState = state,
            snapPosition = SnapPosition.Start
        )

        snapFlingBehavior(
            snapLayoutInfoProvider = snapLayoutInfoProvider,
            decayAnimationSpec = decayAnimationSpec,
            snapAnimationSpec = snapAnimationSpec
        )
    }
}