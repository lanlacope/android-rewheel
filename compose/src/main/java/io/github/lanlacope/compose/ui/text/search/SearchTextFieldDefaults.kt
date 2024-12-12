package io.github.lanlacope.compose.ui.text.search

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
object SearchTextFieldDefaults {
    @Composable
    fun colors(): TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        unfocusedLeadingIconColor = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor,
        focusedLeadingIconColor = OutlinedTextFieldDefaults.colors().focusedIndicatorColor,
        disabledLeadingIconColor = OutlinedTextFieldDefaults.colors().disabledIndicatorColor,
        errorLeadingIconColor = OutlinedTextFieldDefaults.colors().errorIndicatorColor,
        unfocusedTrailingIconColor = OutlinedTextFieldDefaults.colors().unfocusedLeadingIconColor,
        focusedTrailingIconColor = OutlinedTextFieldDefaults.colors().unfocusedLeadingIconColor,
        disabledTrailingIconColor = OutlinedTextFieldDefaults.colors().unfocusedLeadingIconColor,
        errorTrailingIconColor = OutlinedTextFieldDefaults.colors().unfocusedLeadingIconColor
    )
}