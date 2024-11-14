package io.github.lanlacope.rewheel.function

import org.json.JSONArray

@Suppress("unused")
inline fun JSONArray.indexedForEach(action: (Int) -> Unit) {
    for (index in 0 until length()) action(index)
}

@Suppress("unused")
inline fun <R> JSONArray.indexedMap(action: (Int) -> R): List<R> {
    return (0 until length()).map { index -> action(index) }
}