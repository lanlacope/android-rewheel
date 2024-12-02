package io.github.lanlacope.compose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
fun DialogColumn(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    properties: DialogProperties = DialogProperties(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
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

            Column(
                modifier = Modifier.constrainAs(contentRef) {
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(buttonRef.top)
                    width = Dimension.matchParent
                },
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                content = content
            )
        }
    }
}

@Composable
fun DialogColumn(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
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

            Column(
                modifier = Modifier.constrainAs(contentRef) {
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(buttonRef.top)
                    width = Dimension.matchParent
                },
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                content = content
            )
        }
    }
}