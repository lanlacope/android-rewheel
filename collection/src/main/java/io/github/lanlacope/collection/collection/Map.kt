package io.github.lanlacope.collection.collection

fun <K, V> Map<K, V>.keyList(): List<K> {
    return this.keys.toList()
}

fun <K, V> Map<K, V>.forEachIndexed(action: (index: Int, Map.Entry<K, V>) -> Unit) {
    this.entries.forEachIndexed { index, entry ->  action(index, entry) }
}