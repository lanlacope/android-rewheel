package io.github.lanlacope.lanlacopetest

import android.util.Log
import android.widget.ScrollView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.composeable.ui.busy.BusyColumn
import io.github.lanlacope.rewheel.composeable.ui.busy.items
import io.github.lanlacope.rewheel.composeable.ui.busy.manu.BusyManu
import io.github.lanlacope.rewheel.composeable.ui.busy.option.text
import io.github.lanlacope.rewheel.composeable.ui.button.layout.ManuButton
import io.github.lanlacope.rewheel.composeable.ui.dialog.SimpleDialog
import io.github.lanlacope.rewheel.function.forEach
import io.github.lanlacope.rewheel.function.map
import org.json.JSONArray
import org.json.JSONObject

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun View() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var shown by remember { mutableStateOf(false) }

        Button(onClick = { shown = true }) {
            Text(text = "zT")
        }

        SimpleDialog(
            expanded = shown,
            onDismissRequest = { shown = false },
            properties = DialogProperties()
        ) {
            Column(
                modifier = Modifier
                    .imePadding()
                    .systemBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {

                Text(text = "AAA")

                var text by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = {
                        Text(
                            text = "A",
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                color = Gray
                            ),
                            modifier = Modifier.wrapContentSize()
                        )
                    },
                    minLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(all = 8.dp)

                )
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.imePadding()
                ) {
                    Text(text = "AAAAAAAAAAAa")
                }
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