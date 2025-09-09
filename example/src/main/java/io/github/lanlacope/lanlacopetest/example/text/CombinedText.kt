package io.github.lanlacope.lanlacopetest.example.text

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.ui.text.combined.CombinedLinkAnnotation
import io.github.lanlacope.rewheel.ui.text.combined.CombinedLinkInteractionListener
import io.github.lanlacope.rewheel.ui.text.combined.CombinedText
import io.github.lanlacope.rewheel.ui.text.combined.withCombinedLink

@Composable
internal fun ExampleCombinedText() {

    val context = LocalContext.current

    val annotated = buildAnnotatedString {

        append("クリック可能なテキスト\n\n")

        append("1つ目の")
        withCombinedLink(
            link = CombinedLinkAnnotation.Clickable(
                tag = "First",
                styles = TextLinkStyles(
                    style = SpanStyle(color = Color.Red),
                    pressedStyle = SpanStyle(
                        color = Color.Green,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = object : CombinedLinkInteractionListener {
                    override fun onClick(link: LinkAnnotation) {
                        Toast.makeText(context, "1つ目", Toast.LENGTH_SHORT).show()
                    }

                    override fun onLongClick(link: LinkAnnotation) {
                        val tag = (link as LinkAnnotation.Clickable).tag
                        Toast.makeText(context, tag, Toast.LENGTH_SHORT).show()
                    }

                }
            )
        ) {
            append("リンク")
        }

        appendLine()
        append("2つ目の")
        withCombinedLink(
            link = CombinedLinkAnnotation.Clickable(
                tag = "Second",
                styles = TextLinkStyles(
                    style = SpanStyle(color = Color.Blue),
                    pressedStyle = SpanStyle(
                        color = Color.DarkGray,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = object : CombinedLinkInteractionListener {
                    override fun onClick(link: LinkAnnotation) {
                        Toast.makeText(context, "2つ目", Toast.LENGTH_SHORT).show()
                    }

                    override fun onLongClick(link: LinkAnnotation) {
                        val tag = (link as LinkAnnotation.Clickable).tag
                        Toast.makeText(context, tag, Toast.LENGTH_SHORT).show()
                    }

                }
            )
        ) {
            append("リンクだよ")
        }
    }

    CombinedText(
        text = annotated,
        fontSize = 24.sp,
    )
}

@Preview
@Composable
private fun CombinedTextPreview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ExampleCombinedText()
        }
    }
}