package io.github.lanlacope.lanlacopelib.composeable.ui.action.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.rewheel.composeable.ui.click.RowButton

@Composable
fun OptionRadioButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onValueChange: (Boolean) -> Unit = {  },
    text: String,
    textStyle: TextStyle = OptionTextStyleDefault(),
    value: Boolean = false,
    innerPadding: PaddingValues = OptionPaddingValuesDefault()
) {
    LaunchedEffect(value) {
        onValueChange(value)
    }

    RowButton(modifier = modifier,
        onClick = onClick,
        innerPadding = innerPadding
    ) {
        RadioButton(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            selected = value,
            onClick = onClick
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