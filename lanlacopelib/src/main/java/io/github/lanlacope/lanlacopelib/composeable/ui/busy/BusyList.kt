package io.github.lanlacope.widgit.composeable.ui.busy

import androidx.annotation.FloatRange
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset

@Composable
fun BusyList(
    modifier: Modifier,
    // state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues,
    reverseLayout: Boolean,
    isVertical: Boolean,
    flingBehavior: FlingBehavior,
    userScrollEnabled: Boolean,
    beyondBoundsItemCount: Int = 0,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: BusyListScope.() -> Unit,
) {
    val scope = remember { BusyListScopeImpl() }
    scope.content()

    androidx.compose.foundation.layout.Box(modifier = modifier) {
        if (isVertical) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(contentPadding),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment
            ) {
                for (itemContent in scope.items) {
                    itemContent(BusyItemScopeImpl())
                }
            }
        } else {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier.padding(contentPadding),
                horizontalArrangement = horizontalArrangement,
                verticalAlignment = verticalAlignment
            ) {
                for (itemContent in scope.items) {
                    itemContent(BusyItemScopeImpl())
                }
            }
        }
    }
}

@LazyScopeMarker
// @JvmDefaultWithCompatibility
interface BusyListScope {

    val items: MutableList<@Composable BusyItemScope.() -> Unit>

    fun item(
        key: Any? = null,
        contentType: Any? = null,
        content: @Composable BusyItemScope.() -> Unit
    ) {
        items.add(content)
    }

    fun items(
        count: Int,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        itemContent: @Composable BusyItemScope.(index: Int) -> Unit
    ) {
        for (i in 0 until count) {
            items.add { itemContent(i) }
        }
    }

    @ExperimentalFoundationApi
    fun stickyHeader(
        key: Any? = null,
        contentType: Any? = null,
        content: @Composable BusyItemScope.() -> Unit
    )
}

@Stable
private class BusyListScopeImpl : BusyListScope {
    // アイテムのリストを保持
    override val items = mutableListOf<@Composable BusyItemScope.() -> Unit>()

    override fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable BusyItemScope.() -> Unit
    ) {
        items.add(content)
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable BusyItemScope.(index: Int) -> Unit
    ) {
        for (i in 0 until count) {
            items.add { itemContent(i) }
        }
    }

    @ExperimentalFoundationApi
    override fun stickyHeader(
        key: Any?,
        contentType: Any?,
        content: @Composable BusyItemScope.() -> Unit
    ) {
        items.add(content)
    }
}


@Stable
@LazyScopeMarker
interface BusyItemScope {

    fun Modifier.fillParentMaxSize(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.fillParentMaxWidth(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.fillParentMaxHeight(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.animateItem(
        fadeInSpec: FiniteAnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        placementSpec: FiniteAnimationSpec<IntOffset>? = spring(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        ),
        fadeOutSpec: FiniteAnimationSpec<Float>? =
            spring(stiffness = Spring.StiffnessMediumLow),
    ): Modifier = this
}


private class BusyItemScopeImpl : BusyItemScope {
    override fun Modifier.fillParentMaxSize(fraction: Float): Modifier {
        return this.fillMaxSize(fraction)
    }

    override fun Modifier.fillParentMaxWidth(fraction: Float): Modifier {
        return this.fillMaxWidth(fraction)
    }

    override fun Modifier.fillParentMaxHeight(fraction: Float): Modifier {
        return this.fillMaxHeight(fraction)
    }
}