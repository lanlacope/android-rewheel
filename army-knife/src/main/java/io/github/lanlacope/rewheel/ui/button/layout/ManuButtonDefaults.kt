package io.github.lanlacope.rewheel.ui.button.layout

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

data class ManuButtonColors(
    val containerColor: Color,
    val borderColor: Color,
    val textColor: Color,)

@Immutable
object ManuButtonDefaults {
    @Composable
    fun colors(): ManuButtonColors = ManuButtonColors(
        containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
        borderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        textColor = MaterialTheme.colorScheme.onBackground
    )
}