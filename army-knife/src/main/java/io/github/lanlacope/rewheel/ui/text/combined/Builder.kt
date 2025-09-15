package io.github.lanlacope.rewheel.ui.text.combined

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.AnnotatedString.Builder
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle

private val TAG = "CombinedLinkInteractionListener"

interface CombinedLinkInteractionListener : LinkInteractionListener {

    override fun onClick(link: LinkAnnotation)
    fun onLongClick(link: LinkAnnotation)
}

internal fun AnnotatedString.convertCombinedLink(): AnnotatedString {

    val builder = Builder(this.text)

    this.spanStyles.forEach { builder.addStyle(it.item, it.start, it.end) }
    this.paragraphStyles.forEach { builder.addStyle(it.item, it.start, it.end) }

    this.getStringAnnotations(0, length).forEach {
        builder.addStringAnnotation(it.tag, it.item, it.start, it.end)
    }

    this.getLinkAnnotations(0, length).forEach {
        if (it.item.linkInteractionListener is CombinedLinkInteractionListener) {
            when (val link = it.item) {
                is LinkAnnotation.Url -> {
                    builder.addStyle(link.styles?.style ?: SpanStyle(), it.start, it.end)
                    builder.addStringAnnotation(TAG, "", it.start, it.end)
                }
                is LinkAnnotation.Clickable -> {
                    builder.addStyle(link.styles?.style ?: SpanStyle(), it.start, it.end)
                    builder.addStringAnnotation(TAG, "", it.start, it.end)
                }
            }
        } else {
            when (val link = it.item) {
                is LinkAnnotation.Url -> {
                    builder.addLink(link, it.start, it.end)

                }
                is LinkAnnotation.Clickable -> {
                    builder.addLink(link, it.start, it.end)

                }
            }
        }
    }

    return builder.toAnnotatedString()
}

internal fun AnnotatedString.applyPressedStyle(pressedRange: IntRange, pressedStyle: List<AnnotatedString. Range<SpanStyle>>): AnnotatedString {

    val builder = Builder(this)

    pressedStyle.forEach {
        if (pressedRange == it.start .. it.end) {
            builder.addStyle(it.item, it.start, it.end)
        }
    }

    return builder.toAnnotatedString()
}

internal fun AnnotatedString.getPressedStyle(): List<AnnotatedString.Range<SpanStyle>> {

    return this.getLinkAnnotations(0, length)
        .filter { it.item.linkInteractionListener is CombinedLinkInteractionListener }
        .map {
            AnnotatedString.Range(
                it.item.styles?.pressedStyle ?: SpanStyle(),
                it.start,
                it.end
            )
        }

}

internal fun AnnotatedString.getLinkRange(position: Int): IntRange {
    return this.getStringAnnotations(position, position)
        .firstOrNull { it.tag == TAG }
        ?.let { it.start..it.end } ?: IntRange.EMPTY
}

internal fun AnnotatedString.onClick(range: IntRange) {

    val link = this.getLinkAnnotations(start = range.start, end = range.last)
        .firstOrNull { it.item.linkInteractionListener is CombinedLinkInteractionListener }

    val listener = link?.item?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onClick(link.item)
}

internal fun AnnotatedString.onLongClick(range: IntRange) {

    val link = this.getLinkAnnotations(start = range.start, end = range.last)
        .firstOrNull { it.item.linkInteractionListener is CombinedLinkInteractionListener }

    val listener = link?.item?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onLongClick(link.item)
}