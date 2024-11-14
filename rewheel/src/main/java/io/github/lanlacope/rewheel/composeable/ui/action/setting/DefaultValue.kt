package io.github.lanlacope.rewheel.composeable.ui.action.setting

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal fun SettingPaddingValuesDefault(): PaddingValues
= PaddingValues(start = 16.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)

internal fun SettingTextStyleDefault() : TextStyle
        = TextStyle(fontSize = 18.sp)

internal fun SettingSummaryTextStyleDefault() : TextStyle
        = TextStyle(color = Color.Gray)

internal fun SettingValueTextStyleDefault() : TextStyle
= TextStyle(color = Color.Gray, fontSize = 16.sp)