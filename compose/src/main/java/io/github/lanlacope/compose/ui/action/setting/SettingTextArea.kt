package io.github.lanlacope.compose.ui.action.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import io.github.lanlacope.compose.ui.button.RowButton


@Composable
fun SettingTextArea(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = SettingTextStyleDefault(),
    summary: String? = null,
    summaryStyle: TextStyle = SettingSummaryTextStyleDefault(),
    value: String,
    valueStyle: TextStyle = SettingValueTextStyleDefault(),
    onClick: () -> Unit,
    innerPadding: PaddingValues = SettingPaddingValuesDefault(),
) {
    RowButton(
        modifier = modifier,
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

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),

            text = value,
            style = valueStyle,
            fontWeight = FontWeight.Bold
        )
    }
}