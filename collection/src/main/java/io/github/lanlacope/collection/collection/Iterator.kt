package io.github.lanlacope.collection.collection

fun <T> Iterator<T>.asIterable(): Iterable<T> {
    return Iterable { this }
}

fun <T> Iterable<T>.toArrayList(): ArrayList<T> {
    if (this is Collection<T>)
        return this.toArrayList()
    return toCollection(ArrayList<T>())
}