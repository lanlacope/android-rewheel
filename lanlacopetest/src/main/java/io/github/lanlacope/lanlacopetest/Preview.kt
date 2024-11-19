package io.github.lanlacope.lanlacopetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.composeable.ui.dialog.option.CheckBoxDialog
import io.github.lanlacope.rewheel.composeable.ui.dialog.option.RadioButtonDialog
import io.github.lanlacope.rewheel.function.replace

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun View() {
    val context = LocalContext.current


    Column {

        var shownCh by remember { mutableStateOf(false) }
        var shownRd by remember { mutableStateOf(false) }

        Button(
            onClick = {
                shownCh = true
            }
        ) {
            Text(text = "チェックボックス")
        }

        Button(
            onClick = {
                shownRd = true
            }
        ) {
            Text(text = "ラジオボタン")
        }

        val options = remember { mutableStateMapOf("A" to "Aです", "B" to "Bです", "C" to "Cです") }
        val checked = remember { mutableStateListOf("A") }

        if (shownCh) {
            CheckBoxDialog(
                title = "選択",
                options = options,
                checkedOptions = checked,
                onConfirm = {
                    checked.replace(it)
                    shownCh = false
                },
                confirmText = "適用",
                onCancel = { shownCh = false },
                cancelText = "キャンセル"
            )
        }

        var selected by remember { mutableStateOf("A") }

        if (shownRd) {
            RadioButtonDialog(
                title = "選択",
                options = options,
                selectedOption = selected,
                onConfirm = {
                    selected = it
                    shownRd = false
                },
                confirmText = "適用",
                onCancel = { shownRd = false },
                cancelText = "キャンセル"
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            View()
        }
    }
}