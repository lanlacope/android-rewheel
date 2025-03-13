package io.github.lanlacope.rewheel.ui.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun BasicDialog(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    if (expanded) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties
        ) {
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp

            Surface(
                modifier = Modifier
                    .heightIn(max = screenHeight)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { /* do nothing */ },
                    ),
                content = content
            )
        }
    }
}

@Composable
fun BasicDialog(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    if (expanded) {
        Dialog(
            onDismissRequest = onConfirm,
            properties = properties
        ) {
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp

            Surface(
                modifier = Modifier
                    .heightIn(max = screenHeight)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { /* do nothing */ },
                    )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        modifier = Modifier.padding(
                            paddingValues = PaddingValues(
                                start = 8.dp,
                                top = 8.dp
                            )
                        ),
                        text = title,
                        style = TextStyle(fontSize = 24.sp)
                    )

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(weight = 1f, fill = false),
                        content = content
                    )

                    TextButton(
                        onClick = onConfirm,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = confirmText)
                    }
                }
            }
        }
    }
}


@Composable
fun BasicDialog(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    if (expanded) {
        Dialog(
            onDismissRequest = onCancel,
            properties = properties
        ) {
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp

            Surface(
                modifier = Modifier
                    .heightIn(max = screenHeight)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { /* do nothing */ },
                    )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            modifier = Modifier.padding(
                                paddingValues = PaddingValues(
                                    start = 8.dp,
                                    top = 8.dp
                                )
                            ),
                            text = title,
                            style = TextStyle(fontSize = 24.sp)
                        )

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(weight = 1f, fill = false),
                        content = content
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
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
    }
}