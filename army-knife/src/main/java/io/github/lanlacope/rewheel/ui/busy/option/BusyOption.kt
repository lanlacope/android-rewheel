package io.github.lanlacope.rewheel.ui.busy.option

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lanlacope.rewheel.ui.busy.BusyColumn
import io.github.lanlacope.rewheel.ui.busy.BusyItemScope
import io.github.lanlacope.rewheel.ui.busy.BusyListScope

@Composable
fun BusyOption(
    modifier: Modifier = Modifier,
    // state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    userScrollEnabled: Boolean = true,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    content: BusyOptionScope.() -> Unit,
) {
    BusyColumn(
        modifier = modifier,
        // state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
    ) {
        content(BusyOptionScopeImpl(this))
    }
}

interface BusyOptionScope {

    fun option(
        key: Any?,
        contentType: Any?,
        content: @Composable() (BusyItemScope.() -> Unit),
    ) {
        error("The method is not implemented")
    }

    fun options(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable() (BusyItemScope.(index: Int) -> Unit),
    ) {
        error("The method is not implemented")
    }

    @Deprecated("This feature is not implemented yet", level = DeprecationLevel.ERROR)
    @ExperimentalFoundationApi
    fun stickyHeader(
        key: Any?,
        contentType: Any?,
        content: @Composable() (BusyItemScope.() -> Unit),
    )
}


internal class BusyOptionScopeImpl(
    private val busyListScope: BusyListScope,
) : BusyOptionScope, BusyListScope by busyListScope {

    override fun option(
        key: Any?,
        contentType: Any?,
        content: @Composable BusyItemScope.() -> Unit,
    ) {
        busyListScope.item(key, contentType, content)
    }

    override fun options(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable() (BusyItemScope.(index: Int) -> Unit),
    ) {
        busyListScope.items(count, key, contentType, itemContent)
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