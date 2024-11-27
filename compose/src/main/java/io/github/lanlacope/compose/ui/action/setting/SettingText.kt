package io.github.lanlacope.compose.ui.action.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
internal fun SettingText(
    modifier: Modifier,
    text: String,
    textStyle: TextStyle,
    summary: String?,
    summaryStyle: TextStyle,
) {
    Column(modifier = modifier.wrapContentSize()) {

        Text(
            text = text,
            style = textStyle
        )

        if (!summary.isNullOrEmpty()) {
            Text(
                text = summary,
                style = summaryStyle
            )
        }
    }
}