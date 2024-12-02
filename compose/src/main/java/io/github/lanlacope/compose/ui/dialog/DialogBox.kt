package io.github.lanlacope.compose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun DialogBox(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    properties: DialogProperties = DialogProperties(),
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    BasicDialog(
        expanded = expanded,
        onDismissRequest = onConfirm,
        properties = properties,
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

            val (titleRef, contentRef, buttonRef) = createRefs()

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

            Box(
                modifier = Modifier.constrainAs(contentRef) {
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(buttonRef.top)
                    width = Dimension.matchParent
                },
                content = content
            )
        }
    }

    @Composable
    fun DialogBox(
        title: String,
        expanded: Boolean,
        onConfirm: () -> Unit,
        confirmText: String,
        onCancel: () -> Unit,
        cancelText: String,
        properties: DialogProperties = DialogProperties(),
        contentAlignment: Alignment = Alignment.TopStart,
        propagateMinConstraints: Boolean = false,
        content: @Composable BoxScope.() -> Unit,
    ) {
        BasicDialog(
            expanded = expanded,
            onDismissRequest = onCancel,
            properties = properties,
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                val (titleRef, contentRef, buttonRef) = createRefs()

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

                Box(
                    modifier = Modifier.constrainAs(contentRef) {
                        top.linkTo(titleRef.bottom)
                        bottom.linkTo(buttonRef.top)
                        width = Dimension.matchParent
                    },
                    contentAlignment = contentAlignment,
                    propagateMinConstraints = propagateMinConstraints,
                    content = content
                )
            }
        }
    }
}