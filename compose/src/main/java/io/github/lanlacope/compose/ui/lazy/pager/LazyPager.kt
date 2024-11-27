package io.github.lanlacope.compose.ui.lazy.pager

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.snapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
internal fun LazyPager(
    modifier: Modifier,
    state: LazyListState,
    contentPadding: PaddingValues,
    reverseLayout: Boolean,
    isVertical: Boolean,
    userScrollEnabled: Boolean,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: LazyPagerScope.() -> Unit,
) {
    val flingBehavior = rememberLazyPagerFlingBehavior(state = state)

    if (isVertical) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = state,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled
        ) {
            content(LazyPagerScopeImpl(this))
        }
    } else {
        LazyRow(
            modifier = modifier.fillMaxSize(),
            state = state,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled
        ) {
            content(LazyPagerScopeImpl(this))
        }
    }
}

interface LazyPagerScope : LazyListScope {

    override fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable() (LazyItemScope.() -> Unit),
    ) {
        error("The method is not implemented")
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable() (LazyItemScope.(index: Int) -> Unit),
    ) {
        error("The method is not implemented")
    }

    @Deprecated("This feature is not implemented yet", level = DeprecationLevel.ERROR)
    @ExperimentalFoundationApi
    override fun stickyHeader(
        key: Any?,
        contentType: Any?,
        content: @Composable() (LazyItemScope.() -> Unit),
    )
}


internal class LazyPagerScopeImpl(
    private val lazyListScope: LazyListScope,
) : LazyPagerScope, LazyListScope by lazyListScope {

    override fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable LazyItemScope.() -> Unit,
    ) {
        lazyListScope.item(key, contentType, content)
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable() (LazyItemScope.(index: Int) -> Unit),
    ) {
        lazyListScope.items(count, key, contentType, itemContent)
    }

    @Deprecated("This feature is not implemented yet", level = DeprecationLevel.ERROR)
    @ExperimentalFoundationApi
    override fun stickyHeader(
        key: Any?,
        contentType: Any?,
        content: @Composable() (LazyItemScope.() -> Unit),
    ) {
        error("The method is not implemented")
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