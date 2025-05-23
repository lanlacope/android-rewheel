package io.github.lanlacope.rewheel.ui.busy

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO: stateの対応
// TODO: スクロールの対応

@Composable
fun BusyColumn(
    modifier: Modifier = Modifier,
    // state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    content: BusyListScope.() -> Unit,
) {
    BusyList(
        modifier = modifier,
        contentPadding = contentPadding,
        isVertical = true,
        flingBehavior = flingBehavior,
        reverseLayout = reverseLayout,
        userScrollEnabled = userScrollEnabled,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        content = content
    )
}