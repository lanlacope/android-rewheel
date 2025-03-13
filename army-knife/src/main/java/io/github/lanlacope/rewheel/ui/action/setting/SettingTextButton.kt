package io.github.lanlacope.rewheel.ui.action.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import io.github.lanlacope.rewheel.ui.button.RowButton


@Composable
fun SettingTextButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = SettingTextButtonDefaults.TextStyle(),
    summary: String? = null,
    summaryStyle: TextStyle = SettingTextButtonDefaults.SummaryTextStyle(),
    value: String? = null,
    valueStyle: TextStyle = SettingTextButtonDefaults.ValueTextStyle(),
    onClick: () -> Unit,
    innerPadding: PaddingValues = SettingTextButtonDefaults.PaddingValues(),
    colors: SettingTextButtonColors = SettingTextButtonDefaults.colors()
) {
    RowButton(
        modifier = modifier.background(colors.containerColor),
        onClick = onClick,
        horizontalArrangement = Arrangement.SpaceBetween,
        innerPadding = innerPadding
    ) {
        SettingText(
            modifier = Modifier
                .wrapContentSize()
                .weight(weight = 0.7f, fill = false)
                .align(Alignment.CenterVertically),
            text = text,
            textStyle = textStyle.copy(color = colors.textColor),
            summary = summary,
            summaryStyle = summaryStyle.copy(color = colors.summaryColor)
        )
        if (!value.isNullOrEmpty()) {
            Text(
                modifier = Modifier
                    .weight(weight = 0.3f, fill = false)
                    .align(Alignment.CenterVertically),
                text = value,
                style = valueStyle,
                fontWeight = FontWeight.Bold,
                color = colors.valueColor
            )
        }
    }
}