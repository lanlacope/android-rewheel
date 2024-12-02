package io.github.lanlacope.compose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

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
                modifier = Modifier.heightIn(max = screenHeight),
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

            Surface(modifier = Modifier.heightIn(max = screenHeight)) {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                    val (titleRef, contentRef, buttonRef) = createRefs()

                    Surface(
                        modifier = Modifier.constrainAs(contentRef) {
                            top.linkTo(titleRef.bottom)
                            bottom.linkTo(buttonRef.top)
                            width = Dimension.matchParent
                        },
                        content = content
                    )

                    Row(
                        modifier = Modifier
                            .constrainAs(titleRef) {
                                top.linkTo(parent.top)
                                width = Dimension.matchParent
                            }
                            .background(MaterialTheme.colorScheme.background),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = Modifier.padding(paddingValues = DialogPaddingValueDefault()),
                            text = title,
                            style = DialogTitleStyleDefault()
                        )
                    }

                    Row(
                        modifier = Modifier
                            .constrainAs(buttonRef) {
                                bottom.linkTo(parent.bottom)
                                width = Dimension.matchParent
                            }
                            .background(MaterialTheme.colorScheme.background),
                        horizontalArrangement = Arrangement.End
                    ) {
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

            Surface(modifier = Modifier.heightIn(max = screenHeight)) {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                    val (titleRef, contentRef, buttonRef) = createRefs()

                    Surface(
                        modifier = Modifier.constrainAs(contentRef) {
                            top.linkTo(titleRef.bottom)
                            bottom.linkTo(buttonRef.top)
                            width = Dimension.matchParent
                        },
                        content = content
                    )

                    Row(
                        modifier = Modifier
                            .constrainAs(titleRef) {
                                top.linkTo(parent.top)
                                width = Dimension.matchParent
                            }
                            .background(MaterialTheme.colorScheme.background),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = Modifier.padding(paddingValues = DialogPaddingValueDefault()),
                            text = title,
                            style = DialogTitleStyleDefault()
                        )
                    }

                    Row(
                        modifier = Modifier
                            .constrainAs(buttonRef) {
                                bottom.linkTo(parent.bottom)
                                width = Dimension.matchParent
                            }
                            .background(MaterialTheme.colorScheme.background),
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