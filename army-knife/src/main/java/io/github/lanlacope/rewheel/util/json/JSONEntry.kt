package io.github.lanlacope.rewheel.util.json

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

data class JSONEntry(
    val key: String,
    val value: Any?
) {
    override fun toString(): String {
        return JSONObject().put(key, value).toString()
    }

    fun get(): Any {
        return if (value is Any) value else throw JSONException("Value is null")
    }

    fun getBoolean(): Boolean {
        return value.toString().toBooleanStrict()
    }

    fun getDouble(): Double {
        return value.toString().toDouble()
    }

    fun getInt(): Int {
        return value.toString().toInt()
    }

    fun getLong(): Long {
        return value.toString().toLong()
    }

    fun getString(): String {
        return value.toString()
    }

    fun getJSONArray(): JSONArray {
        return JSONArray(value.toString())
    }

    fun getJSONObject(): JSONObject {
        return JSONObject(value.toString())
    }

    fun getOrNull(): Any? {
        return value
    }

    fun getBooleanOrNull(): Boolean? {
        return value.toString().toBooleanStrictOrNull()
    }

    fun getDoubleOrNull(): Double? {
        return value.toString().toDoubleOrNull()
    }

    fun getIntOrNull(): Int? {
        return value.toString().toIntOrNull()
    }

    fun getLongOrNull(): Long? {
        return value.toString().toLongOrNull()
    }

    fun getJSONArrayOrNull(): JSONArray? {
        return try {
            JSONArray(value.toString())
        } catch (e: JSONException) {
            null
        }
    }

    fun getJSONObjectOrNull(): JSONObject? {
        return try {
            JSONObject(value.toString())
        } catch (e: JSONException) {
            null
        }
    }
}
