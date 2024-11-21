package io.github.lanlacope.lanlacopetest.example.busy.manu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.composeable.ui.busy.manu.BusyManu
import io.github.lanlacope.rewheel.composeable.ui.busy.option.text
import io.github.lanlacope.rewheel.composeable.ui.button.layout.ManuButton

@Composable
internal fun ExampleBusyManu() {

    var shown by remember { mutableStateOf(false) }
    val options = listOf(1, 2, 3, 4, 5, 6, 7)
    var selected by remember { mutableStateOf(1) }

    ManuButton(
        modifier = Modifier.wrapContentSize(),
        text = "Option: $selected",
        onClick = { shown = true }
    ) {
        BusyManu(
            expanded = shown,
            onDismissRequest = { shown = false }
        ) {
            text(
                options = options.associateWith { "Option: $it" },
                onClick = {
                    selected = it
                    shown = false
                }
            )
        }
    }
}

@Preview
@Composable
private fun BusyMauPreview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ExampleBusyManu()
        }
    }
}