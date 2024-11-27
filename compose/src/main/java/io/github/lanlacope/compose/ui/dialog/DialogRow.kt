package io.github.lanlacope.compose.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun DialogRow(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    properties: DialogProperties = DialogProperties(),
    content: @Composable RowScope.() -> Unit
) {
    SimpleDialog(
        expanded = expanded,
        onDismissRequest = onConfirm,
        properties = properties,
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                modifier = Modifier.padding(paddingValues = DialogPaddingValueDefault()),
                text = title,
                style = DialogTitleStyleDefault()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                content = content
            )

            TextButton(
                modifier = Modifier.align(Alignment.End),
                onClick = onConfirm
            ) {
                Text(text = confirmText)
            }
        }
    }
}

@Composable
fun DialogRow(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
    content: @Composable RowScope.() -> Unit
) {
    SimpleDialog(
        expanded = expanded,
        onDismissRequest = onCancel,
        properties = properties,
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Text(modifier = Modifier.padding(paddingValues = DialogPaddingValueDefault()),
                text = title,
                style = DialogTitleStyleDefault())

            Row(modifier = Modifier.fillMaxWidth(),
                content = content)

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                TextButton(
                    onClick = onCancel
                ) {
                    Text(text = cancelText)
                }

                TextButton(
                    onClick = onConfirm
                ) {
                    Text(text = confirmText)
                }
            }
        }
    }
}