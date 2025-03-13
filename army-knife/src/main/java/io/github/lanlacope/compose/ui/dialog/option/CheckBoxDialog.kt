package io.github.lanlacope.compose.ui.dialog.option

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.window.DialogProperties
import io.github.lanlacope.compose.ui.dialog.BasicDialog
import io.github.lanlacope.compose.ui.lazy.option.LazyOption
import io.github.lanlacope.compose.ui.lazy.option.checkBoxes
import io.github.lanlacope.compose.util.collection.toggle

@Composable
fun <T> CheckBoxDialog(
    title: String,
    options: Map<T, String>,
    checkedOptions: List<T>,
    expanded: Boolean,
    onConfirm: (List<T>) -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
) {
    val dCheckedOptions = remember(expanded) { checkedOptions.toMutableStateList() }

    BasicDialog(
        title = title,
        expanded = expanded,
        onConfirm = { onConfirm(dCheckedOptions) },
        confirmText = confirmText,
        onCancel = onCancel,
        cancelText = cancelText,
        properties = properties
    ) {
        LazyOption {
            checkBoxes(
                options = options,
                checked = { dCheckedOptions.contains(it) },
                onClick = { dCheckedOptions.toggle(it) }
            )
        }
    }
}

@Composable
fun <T> CheckBoxDialog(
    title: String,
    options: Map<T, String>,
    expanded: Boolean,
    onConfirm: (List<T>) -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
) {
    val checkedOptions = remember(expanded) { mutableStateListOf<T>() }

    BasicDialog(
        title = title,
        expanded = expanded,
        onConfirm = { onConfirm(checkedOptions) },
        confirmText = confirmText,
        onCancel = onCancel,
        cancelText = cancelText,
        properties = properties
    ) {
        LazyOption {
            checkBoxes(
                options = options,
                checked = { checkedOptions.contains(it) },
                onClick = { checkedOptions.toggle(it) }
            )
        }
    }
}