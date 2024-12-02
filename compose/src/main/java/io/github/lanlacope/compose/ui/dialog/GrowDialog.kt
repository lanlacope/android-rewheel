package io.github.lanlacope.compose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun GrowDialog(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    properties: GrowDialogProperties = GrowDialogProperties(),
    content: @Composable () -> Unit,
) {
    if (expanded) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties.properties
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .then(
                        if (properties.dismissOnClickOutside) {
                            Modifier.clickable(
                                onClick = onDismissRequest,
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                        } else Modifier
                    ),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val configuration = LocalConfiguration.current
                val screenHeight = configuration.screenHeightDp.dp
                var bottomSpaceHeight by remember { mutableStateOf(screenHeight / 3) }

                val imeHeight = WindowInsets.ime.asPaddingValues().calculateBottomPadding()

                if (imeHeight > bottomSpaceHeight) {
                    bottomSpaceHeight = imeHeight
                }
                Surface(
                    modifier = Modifier
                        .heightIn(max = screenHeight - bottomSpaceHeight)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { /* do nothing */ },
                        ),
                    content = content
                )
                Spacer(modifier = Modifier.height(bottomSpaceHeight))
            }
        }
    }
}

@Composable
fun GrowDialog(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    properties: GrowDialogProperties = GrowDialogProperties(),
    content: @Composable () -> Unit
) {
    if (expanded) {
        Dialog(
            onDismissRequest = onConfirm,
            properties = properties.properties
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .then(
                        if (properties.dismissOnClickOutside) {
                            Modifier.clickable(
                                onClick = onConfirm,
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                        } else Modifier
                    ),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val configuration = LocalConfiguration.current
                val screenHeight = configuration.screenHeightDp.dp
                var bottomSpaceHeight by remember { mutableStateOf(screenHeight / 3) }

                val imeHeight = WindowInsets.ime.asPaddingValues().calculateBottomPadding()

                if (imeHeight > bottomSpaceHeight) {
                    bottomSpaceHeight = imeHeight
                }
                Surface(
                    modifier = Modifier
                        .heightIn(max = screenHeight - bottomSpaceHeight)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { /* do nothing */ },
                        )
                    ) {
                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                        val (titleRef, contentRef, buttonRef) = createRefs()

                        Surface(
                            modifier = Modifier
                                .constrainAs(contentRef) {
                                    top.linkTo(titleRef.bottom)
                                    bottom.linkTo(buttonRef.top)
                                    width = Dimension.matchParent
                                }
                                .heightIn(max = screenHeight - bottomSpaceHeight),
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
                Spacer(modifier = Modifier.height(bottomSpaceHeight))
            }
        }
    }
}

@Composable
fun GrowDialog(
    title: String,
    expanded: Boolean,
    onConfirm: () -> Unit,
    confirmText: String,
    onCancel: () -> Unit,
    cancelText: String,
    properties: GrowDialogProperties = GrowDialogProperties(),
    content: @Composable () -> Unit
) {
    if (expanded) {
        Dialog(
            onDismissRequest = onCancel,
            properties = properties.properties
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .then(
                        if (properties.dismissOnClickOutside) {
                            Modifier.clickable(
                                onClick = onCancel,
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                        } else Modifier
                    ),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val configuration = LocalConfiguration.current
                val screenHeight = configuration.screenHeightDp.dp
                var bottomSpaceHeight by remember { mutableStateOf(screenHeight / 3) }

                val imeHeight = WindowInsets.ime.asPaddingValues().calculateBottomPadding()

                if (imeHeight > bottomSpaceHeight) {
                    bottomSpaceHeight = imeHeight
                }
                Surface(
                    modifier = Modifier
                        .heightIn(max = screenHeight - bottomSpaceHeight)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { /* do nothing */ },
                        )
                    ) {
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
                Spacer(modifier = Modifier.height(bottomSpaceHeight))
            }
        }
    }
}

/*
 * : DialogProperties by DialogProperties(decorFitsSystemWindows: Boolean = false)
 */
class GrowDialogProperties(
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
    usePlatformDefaultWidth: Boolean = true,
    decorFitsSystemWindows: Boolean = false
) {
    private val dialogProperties = DialogProperties(
        dismissOnBackPress = dismissOnBackPress,
        dismissOnClickOutside = dismissOnClickOutside,
        securePolicy = securePolicy,
        usePlatformDefaultWidth = usePlatformDefaultWidth,
        decorFitsSystemWindows = decorFitsSystemWindows
    )

    val properties: DialogProperties get() = dialogProperties

    val dismissOnBackPress: Boolean get() = dialogProperties.dismissOnBackPress
    val dismissOnClickOutside: Boolean get() = dialogProperties.dismissOnClickOutside
    val securePolicy: SecureFlagPolicy get() = dialogProperties.securePolicy
    val usePlatformDefaultWidth: Boolean get() = dialogProperties.usePlatformDefaultWidth
    val decorFitsSystemWindows: Boolean get() = dialogProperties.decorFitsSystemWindows
}