package io.github.lanlacope.rewheel.util.collection

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap

fun <K, V> Map<K, V>.keyList(): List<K> {
    return this.keys.toList()
}

fun <K, V> Map<K, V>.toMutableStateMap(): SnapshotStateMap<K, V> {
    return mutableStateMapOf<K, V>().apply { putAll(this@toMutableStateMap) }
}