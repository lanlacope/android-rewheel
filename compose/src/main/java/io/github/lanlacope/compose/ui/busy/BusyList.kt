package io.github.lanlacope.compose.ui.busy

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// TODO: stateの対応
// TODO: スクロールの対応

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
    val scope = remember { BusyListScopeImpl() }
    scope.content()

    Box(modifier = modifier) {
        if (isVertical) {
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .then(
                        if (userScrollEnabled) Modifier.verticalScroll(rememberScrollState()) // TODO
                        else Modifier
                    ),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment
            ) {
                if (!reverseLayout) {
                    for (itemContent in scope.items) {
                        Box(modifier = Modifier.padding(contentPadding)) {
                            itemContent(BusyItemScopeImpl())
                        }
                    }
                } else {
                    for (itemContent in scope.items.reversed()) {
                        Box(modifier = Modifier.padding(contentPadding)) {
                            itemContent(BusyItemScopeImpl())
                        }
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(contentPadding)
                    .then(
                        if (userScrollEnabled) Modifier.horizontalScroll(rememberScrollState()) // TODO
                        else Modifier
                    ),
                horizontalArrangement = horizontalArrangement,
                verticalAlignment = verticalAlignment
            ) {
                if (!reverseLayout) {
                    for (itemContent in scope.items) {
                        Box(modifier = Modifier.padding(contentPadding)) {
                            itemContent(BusyItemScopeImpl())
                        }
                    }
                } else {
                    for (itemContent in scope.items.reversed()) {
                        Box(modifier = Modifier.padding(contentPadding)) {
                            itemContent(BusyItemScopeImpl())
                        }
                    }
                }
            }
        }
    }
}

@LazyScopeMarker
// @JvmDefaultWithCompatibility
interface BusyListScope {

    fun item(
        key: Any? = null,
        contentType: Any? = null,
        content: @Composable BusyItemScope.() -> Unit,
    ) {
        error("The method is not implemented")
    }

    fun items(
        count: Int,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        itemContent: @Composable BusyItemScope.(index: Int) -> Unit,
    ) {
        error("The method is not implemented")
    }

    @Deprecated("This feature is not implemented yet", level = DeprecationLevel.ERROR)
    @ExperimentalFoundationApi
    fun stickyHeader(
        key: Any? = null,
        contentType: Any? = null,
        content: @Composable BusyItemScope.() -> Unit,
    )
}

@Stable
internal class BusyListScopeImpl : BusyListScope {

    val items = mutableListOf<@Composable BusyItemScope.() -> Unit>()

    override fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable BusyItemScope.() -> Unit,
    ) {
        items.add(content)
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable BusyItemScope.(index: Int) -> Unit,
    ) {
        for (i in 0 until count) {
            items.add { itemContent(i) }
        }
    }

    @Deprecated("This feature is not implemented yet", level = DeprecationLevel.ERROR)
    @ExperimentalFoundationApi
    override fun stickyHeader(
        key: Any?,
        contentType: Any?,
        content: @Composable() (BusyItemScope.() -> Unit),
    ) {
        error("The method is not implemented")
    }
}
