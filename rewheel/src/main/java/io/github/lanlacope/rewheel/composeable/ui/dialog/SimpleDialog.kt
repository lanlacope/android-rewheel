package io.github.lanlacope.rewheel.composeable.ui.dialog

import androidx.compose.material3.Surface
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

@Composable
fun SimpleDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(content = content)
    }
}