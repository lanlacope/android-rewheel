package io.github.lanlacope.rewheel.ui.text.toolbar

import android.app.SearchManager
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import io.github.lanlacope.rewheel.util.insertText
import io.github.lanlacope.rewheel.util.isNotSelectedAll
import io.github.lanlacope.rewheel.util.isNotSelectedNone
import io.github.lanlacope.rewheel.util.removeSelectedText
import io.github.lanlacope.rewheel.util.selectAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AdvancedTextToolbar(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    copyTitle: String = DynamicTextToolbarDefaults.Title.COPY,
    pasteTitle: String = DynamicTextToolbarDefaults.Title.PASTE,
    cutTitle: String = DynamicTextToolbarDefaults.Title.CUT,
    selectAllTitle: String = DynamicTextToolbarDefaults.Title.SELECT_ALL,
    shareTitle: String = DynamicTextToolbarDefaults.Title.SHARE,
    searchTitle: String = DynamicTextToolbarDefaults.Title.SEARCH,
    translateTitle: String = DynamicTextToolbarDefaults.Title.TRANSLATE,
    browserTitle: String = DynamicTextToolbarDefaults.Title.BROWSER,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()
    val textToolbar = rememberDynamicTextToolbar()

    LaunchedEffect(value) {
        textToolbar.clearActions()

        if (Patterns.WEB_URL.toRegex().matches(value.getSelectedText().text)) {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(value.getSelectedText().text)
            }
            textToolbar.addAction(
                title = browserTitle,
                action = { context.startActivity(intent) }
            )
        }

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
                    scope.launch(Dispatchers.Default) {
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

        if (value.isNotSelectedNone()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val translateIntent = Intent().apply {
                    action = Intent.ACTION_TRANSLATE
                    putExtra(Intent.EXTRA_TEXT, value.getSelectedText().text)
                }
                textToolbar.addAction(
                    title = translateTitle,
                    action = { context.startActivity(translateIntent) }
                )
            }

            val searchIntent = Intent().apply {
                action = Intent.ACTION_WEB_SEARCH
                putExtra(SearchManager.QUERY, value.getSelectedText().text)
            }
            textToolbar.addAction(
                title = searchTitle,
                action = { context.startActivity(searchIntent) }
            )

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, value.getSelectedText().text)
            }
            if (context.packageManager.queryIntentActivities(shareIntent, 0).isNotEmpty()) {
                textToolbar.addAction(
                    title = shareTitle,
                    action = { context.startActivity(shareIntent) }
                )
            }
        }
    }

    CompositionLocalProvider(
        LocalTextToolbar provides textToolbar,
        content = content
    )
}
