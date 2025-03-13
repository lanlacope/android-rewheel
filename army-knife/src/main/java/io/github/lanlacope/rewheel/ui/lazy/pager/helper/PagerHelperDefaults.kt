package io.github.lanlacope.rewheel.ui.lazy.pager.helper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

data class PagerHelperColors(
    val selectedContentColor: Color,
    val unselectedContentColor: Color,
    val selectedTextColor: Color,
    val unselectedTextColor: Color
)

@Immutable
object PagerHelperDefaults {
    @Composable
    fun colors(): PagerHelperColors
    = PagerHelperColors(
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = Color.Transparent,
        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
        unselectedTextColor = MaterialTheme.colorScheme.primary
    )
}