package io.github.lanlacope.rewheel.ui.lazy.option

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LazyOption(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    userScrollEnabled: Boolean = true,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    content: LazyOptionScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
    ) {
        content(LazyOptionScopeImpl(this))
    }
}

interface LazyOptionScope : LazyListScope {

    override fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable() (LazyItemScope.() -> Unit)
    ) {
        error("The method is not implemented")
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable() (LazyItemScope.(index: Int) -> Unit)
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


internal class LazyOptionScopeImpl(
    private val lazyListScope: LazyListScope,
): LazyOptionScope, LazyListScope by lazyListScope {

    override fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable LazyItemScope.() -> Unit
    ) {
        lazyListScope.item(key, contentType, content)
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable() (LazyItemScope.(index: Int) -> Unit)
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

