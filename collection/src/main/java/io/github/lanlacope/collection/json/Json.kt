package io.github.lanlacope.collection.json

import org.json.JSONArray
import org.json.JSONObject

inline fun <reified T> JSONArray.autoGet(): (Int) -> T {
    return when (T::class) {
        Boolean::class -> { index -> getBoolean(index) as T }
        Double::class -> { index -> getDouble(index) as T }
        Int::class -> { index -> getInt(index) as T }
        Long::class -> { index -> getLong(index) as T }
        String::class -> { index -> getString(index) as T }
        JSONArray::class -> { index -> getJSONArray(index) as T }
        JSONObject::class -> { index -> getJSONObject(index) as T }
        Any::class -> { index -> get(index) as T }
        else -> { _ -> throw IllegalArgumentException("Unsupported type: ${T::class}") }
    }
}

inline fun <reified T> JSONArray.autoOpt(fallback: T?): (Int) -> T? {
    return when (T::class) {
        Boolean::class -> { index -> optBoolean(index, fallback as? Boolean ?: false) as? T }
        Double::class -> { index -> optDouble(index, fallback as? Double ?: 0.0) as? T }
        Int::class -> { index -> optInt(index, fallback as? Int ?: 0) as? T }
        Long::class -> { index ->optLong(index, fallback as? Long ?: 0L) as? T }
        String::class -> { index -> optString(index, fallback as? String ?: "") as? T }
        JSONArray::class -> { index -> optJSONArray(index) as? T ?: fallback }
        JSONObject::class -> { index -> optJSONObject(index) as? T ?: fallback }
        Any::class -> { index -> opt(index) as? T ?: fallback }
        else -> { _ -> null }
    }
}

inline fun <reified T> JSONArray.forEach(action: (element: T) -> Unit) {
    val extractor = autoGet<T>()
    for (index in 0 until length()) {
        val element = extractor(index)
        action(element)
    }
}

inline fun <reified T> JSONArray.forEachIndexed(action: (index: Int, element: T) -> Unit) {
    val extractor = autoGet<T>()
    for (index in 0 until length()) {
        val element = extractor(index)
        action(index, element)
    }
}

inline fun <reified T> JSONArray.forEach(fallback: T? = null, action: (element: T?) -> Unit) {
    val extractor = autoOpt(fallback)
    for (index in 0 until length()) {
        val element: T? = extractor(index)
        action(element)
    }
}

inline fun <reified T> JSONArray.forEachIndexed(fallback: T? = null, action: (index: Int, element: T?) -> Unit) {
    val extractor = autoOpt(fallback)
    for (index in 0 until length()) {
        val element: T? = extractor(index)
        action(index, element)
    }
}

inline fun <reified T, R> JSONArray.map(transform: (element: T) -> R): List<R> {
    val extractor = autoGet<T>()
    return (0 until length()).map { index ->
        val element = extractor(index)
        transform(element)
    }
}

inline fun <reified T, R> JSONArray.mapIndexed(transform: (index: Int, element: T) -> R): List<R> {
    val extractor = autoGet<T>()
    return (0 until length()).map { index ->
        val element = extractor(index)
        transform(index, element)
    }
}

inline fun <reified T, R> JSONArray.map(fallback: T? = null, transform: (element: T?) -> R): List<R> {
    val extractor = autoOpt(fallback)
    return (0 until length()).map { index ->
        val element: T? = extractor(index)
        transform(element)
    }
}

inline fun <reified T, R> JSONArray.mapIndexed(fallback: T? = null, transform: (index: Int, element: T?) -> R): List<R> {
    val extractor = autoOpt(fallback)
    return (0 until length()).map { index ->
        val element: T? = extractor(index)
        transform(index, element)
    }
}