package io.github.lanlacope.compose.ui.action.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun CompactOptionCheckBox(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onClick: () -> Unit,
) {
    OptionCheckBox(
        modifier = modifier,
        text = text,
        checked = checked,
        textStyle = TextStyle(),
        onClick = onClick,
        innerPadding = PaddingValues()
    )
}

@Composable
fun CompactOptionOptionRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    OptionRadioButton(
        modifier = modifier,
        text = text,
        selected = selected,
        textStyle = TextStyle(),
        onClick = onClick,
        innerPadding = PaddingValues(),
    )
}

@Composable
fun CompactOptionText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    OptionTextButton(
        modifier = modifier,
        text = text,
        textStyle = TextStyle(),
        onClick = onClick,
        innerPadding = PaddingValues()
    )
}