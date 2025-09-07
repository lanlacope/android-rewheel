package io.github.lanlacope.rewheel.util

fun Appendable.appendBlank(): Appendable = append(' ')

fun Appendable.appendBlank(value: CharSequence?): Appendable = append(value).appendBlank()
