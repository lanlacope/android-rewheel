package io.github.lanlacope.widgit.composeable.ui.lazy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lanlacope.lanlacopelib.composeable.ui.lazy.LazyPager

@Suppress("unused")
@Composable
fun LazyVericalPager(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(all = 0.dp),
    userScrollEnabled: Boolean = true,
    loadSidePage: Boolean = true,
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (loadSidePage) Arrangement.spacedBy(10.dp) else Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit
) {
    LazyPager(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        isVertical = true,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        content = content
    )
}