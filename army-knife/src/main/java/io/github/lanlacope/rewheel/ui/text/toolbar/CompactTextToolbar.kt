package io.github.lanlacope.rewheel.ui.text.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import io.github.lanlacope.rewheel.util.insertText
import io.github.lanlacope.rewheel.util.isNotSelectedAll
import io.github.lanlacope.rewheel.util.isNotSelectedNone
import io.github.lanlacope.rewheel.util.removeSelectedText
import io.github.lanlacope.rewheel.util.selectAll

@Composable
fun CompactTextToolbar(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    copyTitle: String = DynamicTextToolbarDefaults.Title.COPY,
    pasteTitle: String = DynamicTextToolbarDefaults.Title.PASTE,
    cutTitle: String = DynamicTextToolbarDefaults.Title.CUT,
    selectAllTitle: String = DynamicTextToolbarDefaults.Title.SELECT_ALL,
    content: @Composable () -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    val textToolbar = rememberDynamicTextToolbar()

    LaunchedEffect(value) {
        textToolbar.clearActions()

        if (value.isNotSelectedNone()) {
            textToolbar.addAction(
                title = cutTitle,
                action = {
                    val selectedText = value.getSelectedText()
                    clipboardManager.setText(selectedText)
                    onValueChange(value.removeSelectedText())
                }
            )

            textToolbar.addAction(
                title = copyTitle,
                action = {
                    val selectedText = value.getSelectedText()
                    clipboardManager.setText(selectedText)
                }
            )
        }

        textToolbar.addAction(
            title = pasteTitle,
            action = {
                val clipText = clipboardManager.getText() ?: return@addAction
                onValueChange(value.insertText(clipText))
            }
        )

        if (value.isNotSelectedAll()) {
            textToolbar.addAction(
                title = selectAllTitle,
                action = { onValueChange(value.selectAll()) }
            )
        }
    }

    CompositionLocalProvider(
        LocalTextToolbar provides textToolbar,
        content = content
    )
}
