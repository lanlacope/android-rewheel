package io.github.lanlacope.compose.ui.action.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.compose.ui.button.RowButton

@Composable
fun OptionText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = OptionTextStyleDefault(),
    onClick: () -> Unit,
    innerPadding: PaddingValues = OptionPaddingValuesDefault(),
) {
    RowButton(
        modifier = modifier,
        onClick = onClick,
        innerPadding = innerPadding
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically),

            text = text,
            style = textStyle
        )
    }
}