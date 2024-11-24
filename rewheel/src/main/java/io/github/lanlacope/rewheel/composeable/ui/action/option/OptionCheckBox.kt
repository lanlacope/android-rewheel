package io.github.lanlacope.rewheel.composeable.ui.action.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.rewheel.composeable.ui.button.RowButton

@Composable
fun OptionCheckBox(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = OptionTextStyleDefault(),
    checked: Boolean,
    onClick: () -> Unit,
    innerPadding: PaddingValues = OptionPaddingValuesDefault()
) {
    RowButton(modifier = modifier,
        onClick = onClick,
        innerPadding = innerPadding
    ) {
        Checkbox(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            checked = checked,
            onCheckedChange = { onClick() }
        )

        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically),

            text = text,
            style = textStyle
        )

    }
}