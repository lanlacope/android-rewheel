package io.github.lanlacope.compose.ui.dialog

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.solver.widgets.Optimizer

@Composable
fun DialigConstraintLayout(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    properties: DialogProperties = DialogProperties(),
    optimizationLevel: Int = Optimizer.OPTIMIZATION_STANDARD,
    animateChangesSpec: AnimationSpec<Float>? = null,
    finishedAnimationListener: (() -> Unit)? = null,
    content: @Composable ConstraintLayoutScope.() -> Unit,
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

            ConstraintLayout(
                modifier = Modifier.constrainAs(contentRef) {
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(buttonRef.top)
                    width = Dimension.matchParent
                },
                content = content
            )
        }
    }
}

@Composable
fun DialigConstraintLayout(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: DialogProperties = DialogProperties(),
    optimizationLevel: Int = Optimizer.OPTIMIZATION_STANDARD,
    animateChangesSpec: AnimationSpec<Float>? = null,
    finishedAnimationListener: (() -> Unit)? = null,
    content: @Composable ConstraintLayoutScope.() -> Unit,

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

            ConstraintLayout(
                modifier = Modifier.constrainAs(contentRef) {
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(buttonRef.top)
                    width = Dimension.matchParent
                },
                content = content
            )
        }
    }
}