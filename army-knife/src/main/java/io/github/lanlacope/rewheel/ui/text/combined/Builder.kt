package io.github.lanlacope.rewheel.ui.text.combined

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.AnnotatedString.Builder
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle

fun Builder.addCombinedLink(
    link: CombinedLinkAnnotation,
    start: Int,
    end: Int
 ) {
    when(link) {
        is CombinedLinkAnnotation.Url -> addLink(link.linkAnnotation, start, end)
        is CombinedLinkAnnotation.Clickable -> addLink(link.linkAnnotation, start, end)
    }
}

fun Builder.withCombinedLink(
    link: CombinedLinkAnnotation.Clickable,
    block: Builder.() -> Unit
) {
    val start = length
    block()
    val end = length

    addCombinedLink(link, start, end)
}

internal fun AnnotatedString.convertCombinedLink(): AnnotatedString {

    val builder = Builder(this.text)

    this.spanStyles.forEach { builder.addStyle(it.item, it.start, it.end) }
    this.paragraphStyles.forEach { builder.addStyle(it.item, it.start, it.end) }

    this.getStringAnnotations(0, length).forEach {
        builder.addStringAnnotation(it.tag, it.item, it.start, it.end)
    }

    this.getLinkAnnotations(0, length).forEach {
        when (val link = it.item) {
            is LinkAnnotation.Url -> {
                if (!link.url.startsWith(URL_RANGE)) {
                    builder.addLink(link, it.start, it.end)
                } else {
                    builder.addStyle(link.styles?.style ?: SpanStyle(), it.start, it.end)
                    builder.addStringAnnotation(link.url, "", it.start, it.end)
                }
            }
            is LinkAnnotation.Clickable -> {
                if (!link.tag.startsWith(CLICKABLE_RANGE)) {
                    builder.addLink(link, it.start, it.end)
                } else {
                    builder.addStyle(link.styles?.style ?: SpanStyle(), it.start, it.end)
                    builder.addStringAnnotation(link.tag, "", it.start, it.end)
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
        .filter {
            when (val link = it.item) {
                is LinkAnnotation.Url ->  link.url.startsWith(URL_RANGE)
                is LinkAnnotation.Clickable ->  link.tag.startsWith(CLICKABLE_RANGE)
                else -> false
            }
        }
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
        .firstOrNull { it.tag.startsWith(URL_RANGE) || it.tag.startsWith(CLICKABLE_RANGE) }
        ?.let { it.start..it.end } ?: IntRange.EMPTY
}

internal fun AnnotatedString.onClick(range: IntRange) {

    val link = this.getLink(range)

    println(link)
    val listener = link?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onClick(link)
}

internal fun AnnotatedString.onLongClick(range: IntRange) {

    val link = this.getLink(range)

    val listener = link?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onLongClick(link)
}

private fun AnnotatedString.getLink(range: IntRange): LinkAnnotation? {
    return getLinkAnnotations(range.first, range.last)
        .firstOrNull {
            when (val link = it.item) {
                is LinkAnnotation.Url -> link.url.startsWith(URL_RANGE)
                is LinkAnnotation.Clickable -> link.tag.startsWith(CLICKABLE_RANGE)
                else -> false
            }
        }?.let {
            when (val link = it.item) {
                is LinkAnnotation.Url -> link.copy(
                    url = link.url.removePrefix(URL_RANGE)
                )
                is LinkAnnotation.Clickable -> link.copy(
                    tag = link.tag.removePrefix(CLICKABLE_RANGE)
                )
                else -> null
            }
        }

}