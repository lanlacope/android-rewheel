package io.github.lanlacope.rewheel.util.collection

fun <T> Collection<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun <T> MutableCollection<T>.replace(elements: Collection<T>) {
    this.clear()
    this.addAll(elements)
}

fun <T> MutableCollection<T>.toggle(element: T) {
    if (contains(element)) this.remove(element)
    else this.add(element)
}

inline fun <reified T> List<*>.asOrNull(): List<T?> {
    return this.map { it as? T }
}

inline fun <reified T> List<*>.asNotNull(): List<T> {
    return this.mapNotNull { it as T }
}