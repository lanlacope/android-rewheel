package io.github.lanlacope.rewheel.ui.action.setting

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SettingSwitchColors(
    val textColor: Color,
    val summaryColor: Color,
    val containerColor: Color
)

object SettingSwitchDefaults {

    fun TextStyle(): TextStyle = TextStyle(fontSize = 20.sp)
    fun SummaryTextStyle(): TextStyle = TextStyle(fontSize = 14.sp)

    fun PaddingValues(): PaddingValues = PaddingValues(start = 16.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)

    @Composable
    fun colors(): SettingSwitchColors = SettingSwitchColors(
        textColor = MaterialTheme.colorScheme.onBackground,
        summaryColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        containerColor = MaterialTheme.colorScheme.background
    )
}