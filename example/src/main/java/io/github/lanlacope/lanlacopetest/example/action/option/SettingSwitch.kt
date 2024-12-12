package io.github.lanlacope.lanlacopetest.example.action.option

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.compose.ui.action.setting.SettingSwitch
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme

@Composable
internal fun ExampleSettingSwitch() {

   Column(Modifier.fillMaxWidth()) {

       var checked by remember { mutableStateOf(false) }
       SettingSwitch(
           text = "Option",
           summary = "summary",
           checked = checked,
           onClick = { checked = !checked }
       )
   }
}

@Preview
@Composable
private fun SettingSwitchPreview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ExampleSettingSwitch()
        }
    }
}