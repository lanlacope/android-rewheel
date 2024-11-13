package io.github.lanlacope.rewheel.composeable.ui.busy

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun BusyList(
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
    // TODO: reverseLayoutに対応させる
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
        error("The method is not implemented")
    }

    fun items(
        count: Int,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        itemContent: @Composable BusyItemScope.(index: Int) -> Unit
    ) {
        error("The method is not implemented")
    }

    @ExperimentalFoundationApi
    fun stickyHeader(
        key: Any? = null,
        contentType: Any? = null,
        content: @Composable BusyItemScope.() -> Unit
    )
}

@Stable
internal class BusyListScopeImpl : BusyListScope {

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
        content: @Composable() (BusyItemScope.() -> Unit),
    ) {
        TODO("Not yet implemented")
    }
}
