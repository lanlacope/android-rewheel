package io.github.lanlacope.lanlacopelib.composeable.ui.action.option

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.lanlacopelib.composeable.ui.action.SettingText
import io.github.lanlacope.lanlacopelib.composeable.ui.action.setting.SettingTextStyleDefault
import io.github.lanlacope.lanlacopelib.composeable.ui.action.setting.SettingPaddingValuesDefault
import io.github.lanlacope.lanlacopelib.composeable.ui.action.setting.SettingSummaryTextStyleDefault
import io.github.lanlacope.rewheel.composeable.ui.click.Row

@Composable
fun OptionCheckBox(
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

    Row(modifier = modifier,
        onClick = onClick,
        innerPadding = innerPadding
    ) {
        Checkbox(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            checked = value,
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