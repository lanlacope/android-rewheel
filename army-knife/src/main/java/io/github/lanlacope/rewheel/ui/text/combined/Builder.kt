package io.github.lanlacope.rewheel.ui.text.combined

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.AnnotatedString.Builder
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles

internal const val LINK_RANGE = "LinkRange"

internal interface CombinedLinkInteractionListener : LinkInteractionListener {
    fun onClick()
    fun onLongClick()

    override fun onClick(link: LinkAnnotation) {
        error("The method is not implemented")
    }
}

fun Builder.addCombinedLink(
    styles: TextLinkStyles,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    start: Int,
    end: Int
 ) {

    val listener = object : CombinedLinkInteractionListener {
        override fun onClick() {
            onClick?.invoke()
        }

        override fun onLongClick() {
            onLongClick?.invoke()
        }
    }

    addLink(
        clickable = LinkAnnotation.Clickable(
            tag = LINK_RANGE,
            styles = styles,
            linkInteractionListener = listener
        ),
        start = start,
        end = end
    )
}

fun Builder.withCombinedLink(
    styles: TextLinkStyles,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    block: Builder.() -> Unit
) {
    val start = length
    block()
    val end = length

    addCombinedLink(styles, onClick, onLongClick, start, end)
}

internal fun AnnotatedString.getLinkRange(position: Int): IntRange {
    return this.getStringAnnotations(LINK_RANGE, position, position)
        .firstOrNull()
        ?.let { it.start..it.end } ?: IntRange.EMPTY
}

internal fun AnnotatedString.onClick(range: IntRange) {
    val listener = this.getLinkAnnotations(range.first, range.last)
        .firstOrNull {
            it.item is LinkAnnotation.Clickable && (it.item as LinkAnnotation.Clickable).tag == LINK_RANGE
        }
        ?.item
        ?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onClick()
}

internal fun AnnotatedString.onLongClick(range: IntRange) {
    val listener = this.getLinkAnnotations(range.first, range.last)
        .firstOrNull {
            it.item is LinkAnnotation.Clickable && (it.item as LinkAnnotation.Clickable).tag == LINK_RANGE
        }
        ?.item
        ?.linkInteractionListener as? CombinedLinkInteractionListener

    listener?.onLongClick()
}

internal fun AnnotatedString.convertCombinedLink(pressedRange: IntRange): AnnotatedString {

    val builder = Builder(text.length)
    builder.append(this.text)

    this.spanStyles.forEach { builder.addStyle(it.item, it.start, it.end) }
    this.paragraphStyles.forEach { builder.addStyle(it.item, it.start, it.end) }

    this.getStringAnnotations(0, length).forEach {
        builder.addStringAnnotation(it.tag, it.item, it.start, it.end)
    }

    this.getLinkAnnotations(0, length).forEach {
        when (val link = it.item) {
            is LinkAnnotation.Url -> builder.addLink(link, it.start, it.end)
            is LinkAnnotation.Clickable -> {
                if (link.tag != LINK_RANGE) {
                    builder.addLink(link, it.start, it.end)
                } else {
                    if (pressedRange == it.start .. it.end) {
                        builder.addStyle(link.styles?.pressedStyle ?: SpanStyle(), it.start, it.end)
                    }
                    else {
                        builder.addStyle(link.styles?.style ?: SpanStyle(), it.start, it.end)
                        builder.addStringAnnotation(LINK_RANGE, "", it.start, it.end)
                    }
                }
            }
        }
    }

    return builder.toAnnotatedString()
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
                if (link.tag != LINK_RANGE) {
                    builder.addLink(link, it.start, it.end)
                } else {
                    builder.addStyle(link.styles?.style ?: SpanStyle(), it.start, it.end)
                    builder.addStringAnnotation(LINK_RANGE, "", it.start, it.end)
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

internal fun AnnotatedString.getPressedStyle(): List<AnnotatedString. Range<SpanStyle>> {

    return this.getLinkAnnotations(0, length)
        .filter {
            val link = it.item
            link is LinkAnnotation.Clickable && link.tag == LINK_RANGE
        }
        .map {
            AnnotatedString.Range(
                it.item.styles?.pressedStyle ?: SpanStyle(),
                it.start,
                it.end
            )
        }
}
