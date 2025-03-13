package io.github.lanlacope.compose.ui.text.toolbar

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import io.github.lanlacope.compose.util.insertText
import io.github.lanlacope.compose.util.isNotSelectedAll
import io.github.lanlacope.compose.util.isNotSelectedNone
import io.github.lanlacope.compose.util.removeSelectedText
import io.github.lanlacope.compose.util.selectAll

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
    val clipboardManager = LocalClipboardManager.current
    val textToolbar = rememberDynamicTextToolbar()

    LaunchedEffect(value) {
        textToolbar.clearActions()

        if (Patterns.WEB_URL.matcher(value.getSelectedText().text).matches()) {
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
