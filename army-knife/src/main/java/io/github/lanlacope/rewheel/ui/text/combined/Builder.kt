package io.github.lanlacope.rewheel.ui.text.combined

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.AnnotatedString.Builder
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import io.github.lanlacope.rewheel.util.collection.asNotNull

internal const val LINK_RANGE = "LinkRange/"

internal interface CombinedLinkInteractionListener : LinkInteractionListener {

    override fun onClick(link: LinkAnnotation)
    fun onLongClick(link: LinkAnnotation)
}

fun Builder.addCombinedLink(
    tag: String,
    styles: TextLinkStyles,
    onClick: ((link: LinkAnnotation) -> Unit)? = null,
    onLongClick: ((link: LinkAnnotation) -> Unit)? = null,
    start: Int,
    end: Int
 ) {

    val listener = object : CombinedLinkInteractionListener {
        override fun onClick(link: LinkAnnotation) {
            onClick?.invoke(link)
        }

        override fun onLongClick(link: LinkAnnotation) {
            onLongClick?.invoke(link)
        }
    }

    addLink(
        clickable = LinkAnnotation.Clickable(
            tag = LINK_RANGE + tag,
            styles = styles,
            linkInteractionListener = listener
        ),
        start = start,
        end = end
    )
}

fun Builder.withCombinedLink(
    tag: String,
    styles: TextLinkStyles,
    onClick: ((link: LinkAnnotation) -> Unit)? = null,
    onLongClick: ((link: LinkAnnotation) -> Unit)? = null,
    block: Builder.() -> Unit
) {
    val start = length
    block()
    val end = length

    addCombinedLink(tag, styles, onClick, onLongClick, start, end)
}

internal fun AnnotatedString.getLinkRange(position: Int): IntRange {
    return this.getStringAnnotations(position, position)
        .firstOrNull { it.tag.startsWith(LINK_RANGE) }
        ?.let { it.start..it.end } ?: IntRange.EMPTY
}

internal fun AnnotatedString.onClick(range: IntRange) {

    val link = this.getLinkAnnotations(range.first, range.last)
        .asNotNull<LinkAnnotation.Clickable>()
        .firstOrNull { it.tag.startsWith(LINK_RANGE) }
        ?.let {
            LinkAnnotation.Clickable(
                tag = it.tag.removePrefix(LINK_RANGE),
                styles = it.styles,
                linkInteractionListener = it.linkInteractionListener
            )
        }

    val listener = link?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onClick(link)
}

internal fun AnnotatedString.onLongClick(range: IntRange) {

    val link = this.getLinkAnnotations(range.first, range.last)
        .asNotNull<LinkAnnotation.Clickable>()
        .firstOrNull { it.tag.startsWith(LINK_RANGE) }
        ?.let {
            LinkAnnotation.Clickable(
                tag = it.tag.removePrefix(LINK_RANGE),
                styles = it.styles,
                linkInteractionListener = it.linkInteractionListener
            )
        }

    val listener = link?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onLongClick(link)
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
            is LinkAnnotation.Url -> builder.addLink(link, it.start, it.end)
            is LinkAnnotation.Clickable -> {
                if (!link.tag.startsWith(LINK_RANGE)) {
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
            val link = it.item
            link is LinkAnnotation.Clickable && link.tag.startsWith(LINK_RANGE)
        }
        .map {
            AnnotatedString.Range(
                it.item.styles?.pressedStyle ?: SpanStyle(),
                it.start,
                it.end
            )
        }

}
