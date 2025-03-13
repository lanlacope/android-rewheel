package io.github.lanlacope.rewheel.ui.text.manu

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import io.github.lanlacope.rewheel.ui.animation.FadeInAnimated
import io.github.lanlacope.rewheel.ui.text.search.SearchTextFieldDefaults

@Composable
fun OutlinedTextFieldManu(
    text: String,
    onTextChange: (text: String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    useLabel: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = SearchTextFieldDefaults.colors(),
    content: @Composable ColumnScope.() -> Unit
) {
    Box{
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = modifier,
                placeholder = hintText.takeIf { !useLabel && !it.isNullOrEmpty() }?.let {
                    {
                        Text(text = it)
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = Icons.Default.Search.toString()
                    )
                },
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.End) {
                        FadeInAnimated(visible = text.isNotEmpty()) {
                            IconButton(
                                onClick = { onTextChange("") }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = Icons.Default.Clear.toString()
                                )
                            }
                        }
                    }
                },
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                interactionSource = interactionSource,
                colors = colors,
                shape = shape,
            )
            content()
        }
    }
}

@Composable
fun OutlinedTextFieldManu(
    text: String,
    onTextChange: (text: String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hintText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    useLabel: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = SearchTextFieldDefaults.colors(),
    content: @Composable ColumnScope.() -> Unit
) {
    Box{
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = modifier,
                placeholder = hintText.takeIf { !useLabel && !it.isNullOrEmpty() }?.let {
                    {
                        Text(text = it)
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = Icons.Default.Search.toString()
                    )
                },
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.End) {
                        FadeInAnimated(visible = text.isNotEmpty()) {
                            IconButton(
                                onClick = { onTextChange("") }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = Icons.Default.Clear.toString()
                                )
                            }
                        }
                        IconButton(
                            onClick = onClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = Icons.Default.ArrowDropDown.toString()
                            )
                        }
                    }
                },
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                interactionSource = interactionSource,
                colors = colors,
                shape = shape,
            )
            content()
        }
    }
}

@Composable
fun OutlinedTextFieldManu(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    useLabel: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = SearchTextFieldDefaults.colors(),
    content: @Composable ColumnScope.() -> Unit
) {
    Box{
        Column {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                placeholder = hintText.takeIf { !useLabel && !it.isNullOrEmpty() }?.let {
                    {
                        Text(text = it)
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = Icons.Default.Search.toString()
                    )
                },
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.End) {
                        FadeInAnimated(visible = value.text.isNotEmpty()) {
                            IconButton(
                                onClick = { onValueChange(value.copy(text = "")) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = Icons.Default.Clear.toString()
                                )
                            }
                        }
                    }
                },
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                interactionSource = interactionSource,
                colors = colors,
                shape = shape,
            )
            content()
        }
    }
}

@Composable
fun OutlinedTextFieldManu(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hintText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    useLabel: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = SearchTextFieldDefaults.colors(),
    content: @Composable ColumnScope.() -> Unit
) {
    Box{
        Column {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                placeholder = hintText.takeIf { !useLabel && !it.isNullOrEmpty() }?.let {
                    {
                        Text(text = it)
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = Icons.Default.Search.toString()
                    )
                },
                trailingIcon = {
                    Row(horizontalArrangement = Arrangement.End) {
                        FadeInAnimated(visible = value.text.isNotEmpty()) {
                            IconButton(
                                onClick = { onValueChange(value.copy(text = "")) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = Icons.Default.Clear.toString()
                                )
                            }
                        }
                        IconButton(
                            onClick = onClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = Icons.Default.ArrowDropDown.toString()
                            )
                        }
                    }
                },
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                interactionSource = interactionSource,
                colors = colors,
                shape = shape,
            )
            content()
        }
    }
}