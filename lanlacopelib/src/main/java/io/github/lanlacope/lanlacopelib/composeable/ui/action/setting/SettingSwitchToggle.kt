package io.github.lanlacope.lanlacopelib.composeable.ui.action.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.lanlacopelib.composeable.ui.action.SettingText
import io.github.lanlacope.rewheel.composeable.ui.click.RowButton

@Composable
fun SettingSwitchToggle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onValueChange: (Boolean) -> Unit = {  },
    text: String,
    textStyle: TextStyle = SettingTextStyleDefault(),
    summary: String? = null,
    summaryStyle: TextStyle = SettingSummaryTextStyleDefault(),
    value: Boolean = false,
    innerPadding: PaddingValues = SettingPaddingValuesDefault(),
) {
    LaunchedEffect(value) {
        onValueChange(value)
    }

    RowButton(modifier = modifier,
        onClick = onClick,
        horizontalArrangement = Arrangement.SpaceBetween,
        innerPadding = innerPadding
    ) {
        SettingText(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically),

            text = text,
            textStyle = textStyle,
            summary = summary,
            summaryStyle = summaryStyle
        )

        Switch(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            checked = value,
            onCheckedChange = { onClick() }
        )
    }
}