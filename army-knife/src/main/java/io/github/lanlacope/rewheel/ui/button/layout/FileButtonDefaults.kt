package io.github.lanlacope.rewheel.ui.button.layout

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

data class FileButtonColors(
    val containerColor: Color,
    val borderColor: Color,
    val textColor: Color,
    val indicatorColor: Color,
    val errorMessageColor: Color
)

@Immutable
object FileButtonDefaults {
    @Composable
    fun colors(): FileButtonColors = FileButtonColors(
        containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
        borderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        textColor = MaterialTheme.colorScheme.onBackground,
        indicatorColor = MaterialTheme.colorScheme.primary,
        errorMessageColor = MaterialTheme.colorScheme.error
    )
}