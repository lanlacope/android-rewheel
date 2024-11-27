package io.github.lanlacope.collection.collection

fun <K, V> Map<K, V>.keyList(): List<K> {
    return this.keys.toList()
}

// TODO: mutableStateSet, SnapShotStateSetが安定版になるまでのもの
fun <T> MutableCollection<T>.toggle(element: T) {
    if (contains(element)) this.remove(element)
    else this.add(element)
}