package io.github.lanlacope.rewheel.util.json

import io.github.lanlacope.rewheel.util.collection.asIterable
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun JSONObject.toKeyList(): List<String> {
    return this.keys().asIterable().toList()
}

fun JSONObject.toEntryList(): List<JSONEntry> {
    return this.toKeyList().map { key ->
        JSONEntry(key, this.getOrNull(key))
    }
}

fun JSONObject.putEntry(entry: JSONEntry) {
    this.put(entry.key, entry.value)
}

fun JSONObject.getOrNull(key: String): Any? {
    return try {
        this.get(key)
    } catch (e: JSONException) {
        null
    }
}

fun JSONObject.getBooleanOrNull(key: String): Boolean? {
    return try {
        this.getBoolean(key)
    } catch (e: JSONException) {
        null
    }
}

fun JSONObject.getDoubleOrNull(key: String): Double? {
    return try {
        this.getDouble(key)
    } catch (e: JSONException) {
        null
    }
}

fun JSONObject.getIntOrNull(key: String): Int? {
    return try {
        this.getInt(key)
    } catch (e: JSONException) {
        null
    }
}

fun JSONObject.getLongOrNull(key: String): Long? {
    return try {
        this.getLong(key)
    } catch (e: JSONException) {
        null
    }
}

fun JSONObject.getStringOrNull(key: String): String? {
    return try {
        this.getString(key)
    } catch (e: JSONException) {
        null
    }
}

fun JSONObject.getJSONArrayOrNull(key: String): JSONArray? {
    return try {
        this.getJSONArray(key)
    } catch (e: JSONException) {
        null
    }
}

fun JSONObject.getJSONObjectOrNull(key: String): JSONObject? {
    return try {
        this.getJSONObject(key)
    } catch (e: JSONException) {
        null
    }
}

inline fun JSONObject.forEach(action: (key: String, element: Any?) -> Unit) {
    this.toKeyList().forEach { key ->
        action(key, getOrNull(key))
    }
}

inline fun JSONObject.forEachIndexed(action: (index: Int, key: String, element: Any?) -> Unit) {
    this.toKeyList().forEachIndexed() { index, key ->
        action(index, key, getOrNull(key))
    }
}

inline fun <R> JSONObject.map(transform: (key: String, element: Any?) -> R): List<R> {
    return this.toKeyList().map { key ->
        transform(key, getOrNull(key))
    }
}

inline fun <R> JSONObject.mapIndexed(transform: (index: Int, key: String, element: Any?) -> R): List<R> {
    return this.toKeyList().mapIndexed() { index, key ->
        transform(index, key, getOrNull(key))
    }
}