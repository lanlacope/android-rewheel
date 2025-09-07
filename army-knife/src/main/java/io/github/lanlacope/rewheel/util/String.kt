package io.github.lanlacope.rewheel.util

fun StringBuilder.appendBlank(): StringBuilder = append(" ")

fun StringBuilder.appendBlank(text: String): StringBuilder = append(text).appendBlank()