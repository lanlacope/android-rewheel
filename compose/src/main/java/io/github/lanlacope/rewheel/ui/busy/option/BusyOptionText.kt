package io.github.lanlacope.rewheel.ui.busy.option

import androidx.compose.ui.Modifier
import io.github.lanlacope.collection.collection.keyList

inline fun <K> BusyOptionScope.text(
    options: Map<K, String>,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionText(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.textIndexed(
    options: Map<K, String>,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionText(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.animatedText(
    options: Map<K, String>,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionText(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.animatedTextIndexed(
    options: Map<K, String>,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionText(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        text = options[keys[it]]!!
    )
}