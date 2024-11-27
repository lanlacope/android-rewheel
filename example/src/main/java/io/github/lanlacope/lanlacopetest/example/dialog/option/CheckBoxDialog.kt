package io.github.lanlacope.lanlacopetest.example.dialog.option

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.collection.collection.replace
import io.github.lanlacope.compose.ui.dialog.option.CheckBoxDialog

@Composable
internal fun ExampleCheckBoxDialog() {

    var shown by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier.wrapContentSize(),
        onClick = { shown = true }
    ) {
        Text(text = "ダイアログを表示")
    }

    val options = remember { mutableStateListOf(1, 2, 3) }
    val checkedOptions = remember { mutableStateListOf(1) }

    CheckBoxDialog(
        title = "タイトル",
        options = options.associateWith { "Option: $it" },
        checkedOptions = checkedOptions,
        expanded = shown,
        onConfirm = {
            checkedOptions.replace(it)
            shown = false
        },
        confirmText = "適用",
        onCancel = { shown = false },
        cancelText = "木ヤンセル"
    )
}

@Preview
@Composable
private fun CheckBoxDialogPreview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ExampleCheckBoxDialog()
        }
    }
}