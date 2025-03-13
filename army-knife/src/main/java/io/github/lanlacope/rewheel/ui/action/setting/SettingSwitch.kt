package io.github.lanlacope.rewheel.ui.action.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.rewheel.ui.button.RowButton

@Composable
fun SettingSwitch(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = SettingSwitchDefaults.TextStyle(),
    summary: String? = null,
    summaryStyle: TextStyle = SettingSwitchDefaults.SummaryTextStyle(),
    checked: Boolean,
    onClick: () -> Unit,
    innerPadding: PaddingValues = SettingSwitchDefaults.PaddingValues(),
    colors: SettingSwitchColors = SettingSwitchDefaults.colors(),
    checkBoxColors: SwitchColors = SwitchDefaults.colors()
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
                .weight(weight = 1f, fill = false)
                .align(Alignment.CenterVertically),
            text = text,
            textStyle = textStyle.copy(color = colors.textColor),
            summary = summary,
            summaryStyle = summaryStyle.copy(color = colors.summaryColor)
        )

        Switch(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            checked = checked,
            onCheckedChange = { onClick() },
            colors = checkBoxColors
        )
    }
}