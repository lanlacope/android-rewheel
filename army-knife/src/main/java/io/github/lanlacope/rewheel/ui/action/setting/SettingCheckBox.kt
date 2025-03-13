package io.github.lanlacope.rewheel.ui.action.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import io.github.lanlacope.rewheel.ui.button.RowButton

@Composable
fun SettingCheckBox(
    modifier: Modifier = Modifier, text: String,
    textStyle: TextStyle = SettingCheckBoxDefaults.TextStyle(),
    summary: String? = null,
    summaryStyle: TextStyle = SettingCheckBoxDefaults.SummaryTextStyle(),
    checked: Boolean,
    onClick: () -> Unit,
    innerPadding: PaddingValues = SettingCheckBoxDefaults.PaddingValues(),
    colors: SettingCheckBoxColors = SettingCheckBoxDefaults.colors(),
    checkBoxColors: CheckboxColors = CheckboxDefaults.colors(),
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

        Checkbox(
            modifier = Modifier.align(Alignment.CenterVertically),
            checked = checked,
            onCheckedChange = { onClick() },
            colors = checkBoxColors
        )
    }
}