package io.github.lanlacope.widgit.function

import org.json.JSONArray

@Suppress("unused")
inline fun JSONArray.forEachIndexOnly(action: (Int) -> Unit) {
    for (index in 0 until length()) action(index)
}

@Suppress("unused")
inline fun <R> JSONArray.mapIndexOnly(action: (Int) -> R): List<R> {
    return (0 until length()).map { index -> action(index) }
}