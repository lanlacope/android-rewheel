package io.github.lanlacope.rewheel.util

import android.text.SpannableString
import android.text.util.Linkify

enum class LinkifyFlag(val mask: Int) {
    WEB_URLS(Linkify.WEB_URLS),
    EMAIL_ADDRESSES(Linkify.EMAIL_ADDRESSES),
    PHONE_NUMBERS(Linkify.PHONE_NUMBERS)
}

fun SpannableString.addLink(vararg flags: LinkifyFlag): SpannableString {

    val mask = flags.fold(0) { acc, flag -> acc or flag.mask }
    Linkify.addLinks(this, mask)

    return this
}