package io.github.lanlacope.lanlacopetest.example.text

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
import io.github.lanlacope.compose.ui.busy.manu.BusyManu
import io.github.lanlacope.compose.ui.busy.option.text
import io.github.lanlacope.compose.ui.text.search.SortTextField
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme

@Composable
internal fun ExampleSortTextField() {

    var text by remember { mutableStateOf("") }
    var shown by remember { mutableStateOf(false) }
    val options = listOf(1, 2, 3, 4, 5, 6, 7)

    SortTextField(
        text = text,
        onTextChange = { text = it },
        onClick = { shown = true },
        modifier = Modifier.wrapContentSize()
    ) {
        BusyManu(
            expanded = shown,
            onDismissRequest = { shown = false }
        ) {
            text(
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
private fun SortTextFieldPreview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ExampleSortTextField()
        }
    }
}