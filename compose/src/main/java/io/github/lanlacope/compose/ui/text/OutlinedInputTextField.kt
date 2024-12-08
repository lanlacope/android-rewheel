package io.github.lanlacope.compose.ui.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.lanlacope.compose.ui.animation.DrawUpAnimated

@Composable
fun OutlinedInputTextField(
    text: String,
    onTextChange: (text: String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    prefixText: String? = null,
    suffixText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    useLabel: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    var isFocused by remember { mutableStateOf(false) }

    val mColors = remember(isFocused) {
        colors.copy(
            errorLabelColor = if (isFocused) colors.errorLabelColor else colors.errorPlaceholderColor
        )
    }

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier.onFocusChanged { focusState ->
            isFocused = focusState.isFocused
        },
        label = hintText.takeIf { useLabel && !it.isNullOrEmpty() }?.let { {
            Text(text = it)
        } },
        placeholder = hintText.takeIf { !useLabel && !it.isNullOrEmpty() }?.let { {
            Text(text = it)
        } },
        leadingIcon = leadingIcon.takeIf { it != null }?.let { {
            Icon(
                imageVector = it,
                contentDescription = it.toString()
            )
        } },
        trailingIcon = trailingIcon.takeIf { it != null }?.let { {
            Icon(
                imageVector = it,
                contentDescription = it.toString()
            )
        } },
        prefix = prefixText.takeIf { !it.isNullOrEmpty() }?.let { {
            Text(text = it)
        } },
        suffix = suffixText.takeIf { !it.isNullOrEmpty() }?.let { {
            Text(text = it)
        } },
        supportingText = errorText.takeIf { !it.isNullOrEmpty() }?.let { {
            DrawUpAnimated(visible = isError) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = Icons.Default.Info.toString()
                    )
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        } },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = mColors,
        shape = shape,
    )
}