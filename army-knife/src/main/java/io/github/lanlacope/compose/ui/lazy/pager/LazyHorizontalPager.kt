package io.github.lanlacope.compose.ui.lazy.pager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LazyHorizontalPager(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(all = 0.dp),
    userScrollEnabled: Boolean = true,
    loadSidePage: Boolean = true,
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (loadSidePage) Arrangement.spacedBy(10.dp) else Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: LazyPagerScope.() -> Unit,
) {
    LazyPager(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        isVertical = false,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
        content = content
    )
}