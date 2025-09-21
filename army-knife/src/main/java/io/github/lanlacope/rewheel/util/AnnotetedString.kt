package io.github.lanlacope.rewheel.util

import android.text.SpannableString
import android.text.style.URLSpan
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TtsAnnotation
import kotlin.sequences.forEach

fun AnnotatedString.Builder.applyStyle(
    style: SpanStyle, regex: Regex
) {
    regex.findAll(this.toString()).forEach {
        @Suppress("DEPRECATION")
        addStyle(
            style = style,
            start = it.range.start,
            end = it.range.endExclusive
        )
    }
}

fun AnnotatedString.Builder.applyStyle(
    style: ParagraphStyle, regex: Regex
) {
    regex.findAll(this.toString()).forEach {
        @Suppress("DEPRECATION")
        addStyle(
            style = style,
            start = it.range.start,
            end = it.range.endExclusive
        )
    }
}

fun AnnotatedString.Builder.applyStringAnnotation(
    tag: String, annotation: String, regex: Regex
) {
    regex.findAll(this.toString()).forEach {
        @Suppress("DEPRECATION")
        addStringAnnotation(
            tag = tag,
            annotation = annotation,
            start = it.range.start,
            end = it.range.endExclusive
        )
    }
}

fun AnnotatedString.Builder.applyStringAnnotation(
    ttsAnnotation: TtsAnnotation, regex: Regex
) {
    regex.findAll(this.toString()).forEach {
        @Suppress("DEPRECATION")
        addTtsAnnotation(
            ttsAnnotation = ttsAnnotation,
            start = it.range.start,
            end = it.range.endExclusive
        )
    }
}

fun AnnotatedString.Builder.applyLink(
    clickable: LinkAnnotation.Clickable, regex: Regex
) {
    regex.findAll(this.toString()).forEach {
        @Suppress("DEPRECATION")
        addLink(
            clickable = clickable,
            start = it.range.start,
            end = it.range.endExclusive
        )
    }
}

fun AnnotatedString.Builder.applyLink(
    url: LinkAnnotation.Url, regex: Regex
) {
    regex.findAll(this.toString()).forEach {
        @Suppress("DEPRECATION")
        addLink(
            url = url,
            start = it.range.start,
            end = it.range.endExclusive
        )
    }
}

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
