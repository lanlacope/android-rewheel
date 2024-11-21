package io.github.lanlacope.lanlacopetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.composeable.ui.busy.BusyColumn
import io.github.lanlacope.rewheel.composeable.ui.busy.items
import io.github.lanlacope.rewheel.composeable.ui.busy.manu.BusyManu
import io.github.lanlacope.rewheel.composeable.ui.busy.option.text
import io.github.lanlacope.rewheel.composeable.ui.button.layout.ManuButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun View() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var shown by remember { mutableStateOf(false) }
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        var selected by remember { mutableStateOf(1) }

        ManuButton(
            text = selected.toString(),
            onClick = { shown = true }
        ) {
            BusyManu(
                expanded = shown,
                onDismissRequest = { shown = false }
            ) {
                text(
                    options = list.associateWith { "Option: $it" },
                    onClick = {
                        selected = it
                        shown = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewTest() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            View()
        }
    }
}