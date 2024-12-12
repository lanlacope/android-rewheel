package io.github.lanlacope.compose.ui.button.combined

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
inline fun CombinedBoxButton(
    modifier: Modifier = Modifier,
    noinline onClick: (() -> Unit)? = null,
    noinline onLongClick: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(),
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .combinedClickable(
                onClick = onClick ?: { },
                onLongClick = onLongClick
            )
            .padding(paddingValues = innerPadding),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedBoxButton(
    onClick: (() -> Unit)? = null,
    onLongClick: () -> Unit,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .combinedClickable(
                onClick = onClick ?: { },
                onLongClick = onLongClick
            ),
    )
}