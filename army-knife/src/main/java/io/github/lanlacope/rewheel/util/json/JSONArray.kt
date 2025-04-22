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

inline fun JSONArray.forEach(action: (element: Any?) -> Unit) {
    for (index in 0 until length()) {
        action(getOrNull(index))
    }
}

inline fun JSONArray.forEachIndexed(action: (index: Int, element: Any?) -> Unit) {
    for (index in 0 until length()) {
        action(index, getOrNull(index))
    }
}

inline fun <R> JSONArray.map(transform: (element: Any?) -> R): List<R> {
    return (0 until length()).map { index ->
        transform(getOrNull(index))
    }
}

inline fun <R> JSONArray.mapIndexed(transform: (index: Int, element: Any?) -> R): List<R> {
    return (0 until length()).map { index ->
        transform(index, getOrNull(index))
    }
}