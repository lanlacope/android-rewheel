package io.github.lanlacope.collection.collection

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