package io.github.lanlacope.rewheel.composeable.ui.click

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

@Composable
inline fun Box(
    noinline onClick: () -> Unit,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .padding(paddingValues = innerPadding)
            .clickable(
                onClick = onClick
            ),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
inline fun Box(
    modifier: Modifier = Modifier,
    noinline onClick: (() -> Unit)? = null,
    noinline onLongClick: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(),
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .padding(paddingValues = innerPadding)
            .combinedClickable(
                onClick = onClick?: { },
                onLongClick = onLongClick
            ),

        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}

@Composable
fun Box(
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clickable (
                onClick = onClick
            )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Box(
    onClick: (() -> Unit)? = null,
    onLongClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .combinedClickable(
                onClick = onClick?: { },
                onLongClick = onLongClick
            ),

        )
}