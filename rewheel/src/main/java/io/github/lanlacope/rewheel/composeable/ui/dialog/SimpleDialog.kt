package io.github.lanlacope.rewheel.composeable.ui.dialog

import androidx.compose.material3.Surface
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

@Composable
fun SimpleDialog(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    content: @Composable () -> Unit
) {
    if (expanded) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties
        ) {
            Surface(content = content)
        }
    }
}