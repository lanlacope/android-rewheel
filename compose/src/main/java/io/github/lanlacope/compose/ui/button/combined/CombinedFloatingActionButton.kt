package io.github.lanlacope.compose.ui.button.combined

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun CombinedFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onLongClick: () -> Unit,
    shape: Shape = FloatingActionButtonDefaults.shape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    // elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    val tonalElevation = 0.0.dp  // elevation.tonalElevation() ?: 0.dp
    val shadowElevation =
        0.0.dp // elevation.shadowElevation(interactionSource = interactionSource).value 0.dp

    CombinedSurface(
        onClick = onClick,
        onLongClick = onLongClick,
        modifier = modifier.semantics { role = Role.Button },
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        interactionSource = interactionSource,
    ) {
        ProvideContentColorTextStyle(
            contentColor = contentColor,
            textStyle = MaterialTheme.typography.labelLarge
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 56.0.dp,
                        minHeight = 56.0.dp
                    ),
                contentAlignment = Alignment.Center,
            ) {
                content()
            }
        }
    }
}