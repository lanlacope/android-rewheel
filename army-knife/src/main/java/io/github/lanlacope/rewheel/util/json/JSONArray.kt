package io.github.lanlacope.rewheel.util.json

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun JSONArray.getOrNull(index: Int): Any? {
    return try {
        this.get(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.getBooleanOrNull(index: Int): Boolean? {
    return try {
        this.getBoolean(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.getDoubleOrNull(index: Int): Double? {
    return try {
        this.getDouble(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.getIntOrNull(index: Int): Int? {
    return try {
        this.getInt(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.getLongOrNull(index: Int): Long? {
    return try {
        this.getLong(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.getStringOrNull(index: Int): String? {
    return try {
        this.getString(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.getJSONArrayOrNull(index: Int): JSONArray? {
    return try {
        this.getJSONArray(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.getJSONObjectOrNull(index: Int): JSONObject? {
    return try {
        this.getJSONObject(index)
    } catch (e: JSONException) {
        null
    }
}

fun JSONArray.forEach(action: (element: Any?) -> Unit) {
    for (index in 0 until length()) {
        action(getOrNull(index))
    }
}

fun JSONArray.forEachIndexed(action: (index: Int, element: Any?) -> Unit) {
    for (index in 0 until length()) {
        action(index, getOrNull(index))
    }
}

fun <R> JSONArray.map(transform: (element: Any?) -> R): List<R> {
    return (0 until length()).map { index ->
        transform(getOrNull(index))
    }
}

fun <R> JSONArray.mapIndexed(transform: (index: Int, element: Any?) -> R): List<R> {
    return (0 until length()).map { index ->
        transform(index, getOrNull(index))
    }
}


/*
inline fun <reified T> JSONArray.autoGet(): (index: Int) -> T {
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

inline fun <reified T> JSONArray.autoOpt(fallback: T?): (index: Int) -> T? {
    return when (T::class) {
        Boolean::class -> { index -> optBoolean(index, fallback as? Boolean ?: false) as? T }
        Double::class -> { index -> optDouble(index, fallback as? Double ?: 0.0) as? T }
        Int::class -> { index -> optInt(index, fallback as? Int ?: 0) as? T }
        Long::class -> { index -> optLong(index, fallback as? Long ?: 0L) as? T }
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

inline fun <reified T> JSONArray.forEachIndexed(
    fallback: T? = null,
    action: (index: Int, element: T?) -> Unit,
) {
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

inline fun <reified T, R> JSONArray.map(
    fallback: T? = null,
    transform: (element: T?) -> R,
): List<R> {
    val extractor = autoOpt(fallback)
    return (0 until length()).map { index ->
        val element: T? = extractor(index)
        transform(element)
    }
}

inline fun <reified T, R> JSONArray.mapIndexed(
    fallback: T? = null,
    transform: (index: Int, element: T?) -> R,
): List<R> {
    val extractor = autoOpt(fallback)
    return (0 until length()).map { index ->
        val element: T? = extractor(index)
        transform(index, element)
    }
}

 */