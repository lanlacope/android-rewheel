package io.github.lanlacope.compose.ui.action.setting

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SettingCheckBoxColors(
    val textColor: Color,
    val summaryColor: Color,
    val containerColor: Color
)

object SettingCheckBoxDefaults {

    fun TextStyle(): TextStyle = TextStyle(fontSize = 18.sp)
    fun SummaryTextStyle(): TextStyle = TextStyle()

    fun PaddingValues(): PaddingValues = PaddingValues(start = 16.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)

    @Composable
    fun colors(): SettingCheckBoxColors = SettingCheckBoxColors(
        textColor = MaterialTheme.colorScheme.onBackground,
        summaryColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        containerColor = MaterialTheme.colorScheme.background
    )
}