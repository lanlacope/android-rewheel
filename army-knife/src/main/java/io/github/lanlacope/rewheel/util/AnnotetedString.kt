package io.github.lanlacope.rewheel.util

import android.text.SpannableString
import android.text.style.URLSpan
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.TextLinkStyles


fun AnnotatedString.Companion.fromSpannable(
    spannable: SpannableString,
    linkStyles: TextLinkStyles? = null,
    linkInteractionListener: LinkInteractionListener? = null,
): AnnotatedString {

    val builder = AnnotatedString.Builder(spannable.toString())

    spannable.getSpans(0, spannable.length, URLSpan::class.java).forEach {
        builder.addLink(
            url = LinkAnnotation.Url(
                url = it.url,
                styles = linkStyles,
                linkInteractionListener = linkInteractionListener,
            ),
            start = spannable.getSpanStart(it),
            end = spannable.getSpanEnd(it),
        )
    }

    return builder.toAnnotatedString()
}
