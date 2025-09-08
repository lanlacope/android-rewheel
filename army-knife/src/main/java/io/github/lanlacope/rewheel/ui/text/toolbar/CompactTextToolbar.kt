package io.github.lanlacope.rewheel.ui.text.toolbar

import android.content.ClipData
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import io.github.lanlacope.rewheel.util.insertText
import io.github.lanlacope.rewheel.util.isNotSelectedAll
import io.github.lanlacope.rewheel.util.isNotSelectedNone
import io.github.lanlacope.rewheel.util.removeSelectedText
import io.github.lanlacope.rewheel.util.selectAll
import kotlinx.coroutines.launch

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
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()
    val textToolbar = rememberDynamicTextToolbar()

    LaunchedEffect(value) {
        textToolbar.clearActions()

        if (value.isNotSelectedNone()) {
            textToolbar.addAction(
                title = cutTitle,
                action = {
                    scope.launch {
                        val selectedText = value.getSelectedText()
                        val clipEntry = ClipEntry(ClipData.newPlainText(selectedText, selectedText))
                        clipboard.setClipEntry(clipEntry)
                        onValueChange(value.removeSelectedText())
                    }
                }
            )

            textToolbar.addAction(
                title = copyTitle,
                action = {
                    scope.launch {
                        val selectedText = value.getSelectedText()
                        val clipEntry = ClipEntry(ClipData.newPlainText(selectedText, selectedText))
                        clipboard.setClipEntry(clipEntry)
                    }
                }
            )
        }

        textToolbar.addAction(
            title = pasteTitle,
            action = {
                scope.launch {
                    val clipEntry = clipboard.getClipEntry()
                    val clipText = clipEntry?.clipData?.getItemAt(0)?.text?.toString() ?: return@launch
                    onValueChange(value.insertText(AnnotatedString(clipText)))
                }
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
