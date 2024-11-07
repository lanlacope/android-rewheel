package io.github.lanlacope.widgit.function

@Suppress("unused")
fun <T> Iterable<T>.toArrayList(): ArrayList<T> {
    if (this is Collection<T>)
        return this.toArrayList()
    return toCollection(ArrayList<T>())
}

@Suppress("unused")
fun <T> Collection<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}