package io.github.lanlacope.rewheel.function

fun <T> Iterable<T>.toArrayList(): ArrayList<T> {
    if (this is Collection<T>)
        return this.toArrayList()
    return toCollection(ArrayList<T>())
}

fun <T> Collection<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun <T> MutableCollection<T>.replace(elements: Collection<T>) {
    this.clear()
    this.addAll(elements)
}

fun <K, V> Map<K, V>.keyList(): List<K> {
    return this.keys.toList()
}

// TODO: mutableStateSet, SnapShotStateSetが安定版になるまでのもの
fun <T> MutableCollection<T>.toggle(element: T) {
    if (contains(element)) this.remove(element)
    else this.add(element)
}