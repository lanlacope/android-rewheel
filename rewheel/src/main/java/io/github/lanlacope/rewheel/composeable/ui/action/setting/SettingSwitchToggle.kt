package io.github.lanlacope.rewheel.composeable.ui.action.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.rewheel.composeable.ui.action.SettingText
import io.github.lanlacope.rewheel.composeable.ui.button.RowButton

@Composable
fun SettingSwitchToggle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    textStyle: TextStyle = SettingTextStyleDefault(),
    summary: String? = null,
    summaryStyle: TextStyle = SettingSummaryTextStyleDefault(),
    checked: Boolean = false,
    innerPadding: PaddingValues = SettingPaddingValuesDefault(),
) {
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
            checked = checked,
            onCheckedChange = { onClick() }
        )
    }
}