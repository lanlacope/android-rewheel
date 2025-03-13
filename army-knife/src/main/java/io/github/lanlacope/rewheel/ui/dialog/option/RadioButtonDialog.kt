package io.github.lanlacope.rewheel.ui.dialog.option

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.DialogProperties
import io.github.lanlacope.rewheel.ui.dialog.BasicDialog
import io.github.lanlacope.rewheel.ui.lazy.option.LazyOption
import io.github.lanlacope.rewheel.ui.lazy.option.radioButtons
import io.github.lanlacope.rewheel.util.collection.keyList

@Composable
fun <T> RadioButtonDialog(
    title: String,
    options: Map<T, String>,
    selectedOption: T,
    expanded: Boolean,
    onConfirm: (T) -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
) {
    var dSelectedOption by remember(expanded) { mutableStateOf(selectedOption) }

    BasicDialog(
        title = title,
        expanded = expanded,
        onConfirm = { onConfirm(dSelectedOption) },
        confirmText = confirmText,
        onCancel = onCancel,
        cancelText = cancelText,
        properties = properties
    ) {
        LazyOption {
            radioButtons(
                options = options,
                selected = { dSelectedOption == it },
                onClick = { dSelectedOption = it }
            )
        }
    }
}

@Composable
fun <T> RadioButtonDialog(
    title: String,
    options: Map<T, String>,
    expanded: Boolean,
    onConfirm: (T) -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
) {
    var selectedOption by remember(expanded) { mutableStateOf(options.keyList()[0]) }

    BasicDialog(
        title = title,
        expanded = expanded,
        onConfirm = { onConfirm(selectedOption) },
        confirmText = confirmText,
        onCancel = onCancel,
        cancelText = cancelText,
        properties = properties
    ) {
        LazyOption {
            radioButtons(
                options = options,
                selected = { selectedOption == it },
                onClick = { selectedOption = it }
            )
        }
    }
}