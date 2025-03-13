package io.github.lanlacope.lanlacopetest.example.text

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
import io.github.lanlacope.rewheel.ui.busy.manu.BusyManu
import io.github.lanlacope.rewheel.ui.busy.option.texts
import io.github.lanlacope.rewheel.ui.text.manu.TextFieldManu
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme

@Composable
internal fun ExampleTextFieldManu() {

    var text by remember { mutableStateOf("") }
    var shown by remember { mutableStateOf(false) }
    val options = listOf(1, 2, 3, 4, 5, 6, 7)

    TextFieldManu(
        text = text,
        onTextChange = { text = it },
        onClick = { shown = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        BusyManu(
            expanded = shown,
            onDismissRequest = { shown = false }
        ) {
            texts(
                options = options.associateWith { "Option: $it" },
                onClick = {
                    text = "Option: $it"
                    shown = false
                }
            )
        }
    }
}

@Preview
@Composable
private fun TextFieldManuPreview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ExampleTextFieldManu()
        }
    }
}