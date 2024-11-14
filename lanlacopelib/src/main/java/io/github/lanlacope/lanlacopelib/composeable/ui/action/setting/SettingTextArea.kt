package io.github.lanlacope.lanlacopelib.composeable.ui.action.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import io.github.lanlacope.lanlacopelib.composeable.ui.action.SettingText
import io.github.lanlacope.rewheel.composeable.ui.click.Row


@Composable
fun SettingTextArea(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit = {  },
    text: String,
    textStyle: TextStyle = SettingTextStyleDefault(),
    summary: String? = null,
    summaryStyle: TextStyle = SettingSummaryTextStyleDefault(),
    value: String = "",
    valueStyle: TextStyle = SettingValueTextStyleDefault(),
    innerPadding: PaddingValues = SettingPaddingValuesDefault()
) {
    LaunchedEffect(value) {
        onValueChange(value)
    }

    Row(modifier = modifier,
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