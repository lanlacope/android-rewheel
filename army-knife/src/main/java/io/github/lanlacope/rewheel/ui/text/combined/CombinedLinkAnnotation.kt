package io.github.lanlacope.rewheel.ui.text.combined

import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.TextLinkStyles

internal const val URL_RANGE = "UrlRange/"
internal const val CLICKABLE_RANGE = "ClickableRange/"

interface CombinedLinkInteractionListener : LinkInteractionListener {

    override fun onClick(link: LinkAnnotation)
    fun onLongClick(link: LinkAnnotation)
}

sealed class CombinedLinkAnnotation {

    internal abstract val linkAnnotation: LinkAnnotation
    abstract val styles: TextLinkStyles?
    abstract val linkInteractionListener: CombinedLinkInteractionListener?

    class Url(
        val url: String,
        override val styles: TextLinkStyles?,
        override val linkInteractionListener: CombinedLinkInteractionListener?
    ) : CombinedLinkAnnotation() {

        override val linkAnnotation: LinkAnnotation.Url = LinkAnnotation.Url(
            url = URL_RANGE + url,
            styles = styles,
            linkInteractionListener = linkInteractionListener,
        )

        override fun hashCode(): Int = linkAnnotation.hashCode()
        override fun equals(other: Any?): Boolean = linkAnnotation.equals(other)
        override fun toString(): String {
            return "CombinedLinkAnnotation.Url(url=$url)"
        }
    }

    class Clickable(
        val tag: String,
        override val styles: TextLinkStyles?,
        override val linkInteractionListener: CombinedLinkInteractionListener?
    ) : CombinedLinkAnnotation() {

        override val linkAnnotation: LinkAnnotation.Clickable = LinkAnnotation.Clickable(
            tag = CLICKABLE_RANGE + tag,
            styles = styles,
            linkInteractionListener = linkInteractionListener,
        )

        override fun hashCode(): Int = linkAnnotation.hashCode()
        override fun equals(other: Any?): Boolean = linkAnnotation.equals(other)
        override fun toString(): String {
            return "CombinedLinkAnnotation.Clickable(tag=$tag)"
        }
    }
}


