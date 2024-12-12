package io.github.lanlacope.compose.ui.button.combined

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
inline fun CombinedColumnButton(
    modifier: Modifier = Modifier,
    noinline onClick: (() -> Unit)? = null,
    noinline onLongClick: (() -> Unit)? = null,
    noinline onDoubleClick: (() -> Unit)? = null,
    innerPadding: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .combinedClickable(
                onClick = onClick ?: { },
                onLongClick = onLongClick,
                onDoubleClick = onDoubleClick
            )
            .padding(paddingValues = innerPadding),

        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}