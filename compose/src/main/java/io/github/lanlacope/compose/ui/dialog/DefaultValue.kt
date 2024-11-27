package io.github.lanlacope.compose.ui.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

internal fun DialogPaddingValueDefault() : PaddingValues
        = PaddingValues(start = 8.dp, top = 8.dp)

internal fun DialogTitleStyleDefault() : TextStyle
        = TextStyle(fontSize = 24.sp)