package io.github.lanlacope.rewheel.util.json

import io.github.lanlacope.rewheel.util.collection.asIterable
import org.json.JSONArray
import org.json.JSONObject

fun JSONObject.keyList(): List<String> {
    return this.keys().asIterable().toList()
}

inline fun <reified T> JSONObject.autoGet(): (key: String) -> T {
    return when (T::class) {
        Boolean::class -> { key -> getBoolean(key) as T }
        Double::class -> { key -> getDouble(key) as T }
        Int::class -> { key -> getInt(key) as T }
        Long::class -> { key -> getLong(key) as T }
        String::class -> { key -> getString(key) as T }
        JSONArray::class -> { key -> getJSONArray(key) as T }
        JSONObject::class -> { key -> getJSONObject(key) as T }
        Any::class -> { key -> get(key) as T }
        else -> { _ -> throw IllegalArgumentException("Unsupported type: ${T::class}") }
    }
}

inline fun <reified T> JSONObject.autoOpt(fallback: T?): (key: String) -> T? {
    return when (T::class) {
        Boolean::class -> { key -> optBoolean(key, fallback as? Boolean ?: false) as? T }
        Double::class -> { key -> optDouble(key, fallback as? Double ?: 0.0) as? T }
        Int::class -> { key -> optInt(key, fallback as? Int ?: 0) as? T }
        Long::class -> { key -> optLong(key, fallback as? Long ?: 0L) as? T }
        String::class -> { key -> optString(key, fallback as? String ?: "") as? T }
        JSONArray::class -> { key -> optJSONArray(key) as? T ?: fallback }
        JSONObject::class -> { key -> optJSONObject(key) as? T ?: fallback }
        Any::class -> { key -> opt(key) as? T ?: fallback }
        else -> { _ -> null }
    }
}

inline fun <reified T> JSONObject.forEach(action: (key: String, element: T) -> Unit) {
    val extractor = autoGet<T>()
    this.keyList().forEach { key ->
        val element = extractor(key)
        action(key, element)
    }
}

inline fun <reified T> JSONObject.forEachIndexed(action: (index: Int, key: String, element: T) -> Unit) {
    val extractor = autoGet<T>()
    this.keyList().forEachIndexed() { index, key ->
        val element = extractor(key)
        action(index, key, element)
    }
}

inline fun <reified T> JSONObject.forEach(
    fallback: T? = null,
    action: (key: String, element: T?) -> Unit,
) {
    val extractor = autoOpt(fallback)
    this.keyList().forEach { key ->
        val element = extractor(key)
        action(key, element)
    }
}

inline fun <reified T> JSONObject.forEachIndexed(
    fallback: T? = null,
    action: (index: Int, key: String, element: T?) -> Unit,
) {
    val extractor = autoOpt(fallback)
    this.keyList().forEachIndexed() { index, key ->
        val element = extractor(key)
        action(index, key, element)
    }
}

inline fun <reified T, R> JSONObject.map(transform: (key: String, element: T) -> R): List<R> {
    val extractor = autoGet<T>()
    return this.keyList().map { key ->
        val element = extractor(key)
        transform(key, element)
    }
}

inline fun <reified T, R> JSONObject.mapIndexed(transform: (index: Int, key: String, element: T) -> R): List<R> {
    val extractor = autoGet<T>()
    return this.keyList().mapIndexed() { index, key ->
        val element = extractor(key)
        transform(index, key, element)
    }
}

inline fun <reified T, R> JSONObject.map(
    fallback: T? = null,
    transform: (key: String, element: T?) -> R,
): List<R> {
    val extractor = autoOpt(fallback)
    return this.keyList().map { key ->
        val element = extractor(key)
        transform(key, element)
    }
}

inline fun <reified T, R> JSONObject.mapIndexed(
    fallback: T? = null,
    transform: (index: Int, key: String, element: T?) -> R,
): List<R> {
    val extractor = autoOpt(fallback)
    return this.keyList().mapIndexed() { index, key ->
        val element = extractor(key)
        transform(index, key, element)
    }
}
