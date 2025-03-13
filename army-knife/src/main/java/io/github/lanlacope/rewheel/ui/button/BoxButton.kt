package io.github.lanlacope.rewheel.composeable.ui.click

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
inline fun BoxButton(
    noinline onClick: () -> Unit,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .padding(paddingValues = innerPadding),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}

@Composable
fun BoxButton(
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
    )
}