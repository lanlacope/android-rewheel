package io.github.lanlacope.rewheel.ui.text.input

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

internal fun TextFieldColors.labelFocuseObserve(text: String, isFocused: Boolean):TextFieldColors
= this.copy(
    errorLabelColor =if (isFocused || text.isNotEmpty()) this.errorLabelColor else this.errorPlaceholderColor
)

@Immutable
object InputTextFieldDefaults {
    @Composable
    fun colors(): TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        unfocusedLeadingIconColor = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor,
        focusedLeadingIconColor = OutlinedTextFieldDefaults.colors().focusedIndicatorColor,
        disabledLeadingIconColor = OutlinedTextFieldDefaults.colors().disabledIndicatorColor,
        errorLeadingIconColor = OutlinedTextFieldDefaults.colors().errorIndicatorColor,
        unfocusedTrailingIconColor = OutlinedTextFieldDefaults.colors().unfocusedIndicatorColor,
        focusedTrailingIconColor = OutlinedTextFieldDefaults.colors().focusedIndicatorColor,
        disabledTrailingIconColor = OutlinedTextFieldDefaults.colors().disabledIndicatorColor,
        errorTrailingIconColor = OutlinedTextFieldDefaults.colors().errorIndicatorColor
    )
}