package io.github.lanlacope.rewheel.composeable.ui.action.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.rewheel.composeable.ui.button.RowButton

@Composable
fun OptionRadioButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    textStyle: TextStyle = OptionTextStyleDefault(),
    selected: Boolean = false,
    innerPadding: PaddingValues = OptionPaddingValuesDefault()
) {
    RowButton(modifier = modifier,
        onClick = onClick,
        innerPadding = innerPadding
    ) {
        RadioButton(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            selected = selected,
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