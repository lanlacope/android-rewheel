package io.github.lanlacope.compose.ui.lazy.option

import androidx.compose.ui.Modifier
import io.github.lanlacope.collection.collection.keyList
import io.github.lanlacope.compose.ui.action.option.OptionRadioButton

inline fun <K> LazyOptionScope.radioButton(
    options: Map<K, String>,
    noinline selected: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionRadioButton(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        selected = selected(keys[it]),
        text = options[keys[it]]!!
    )
}

inline fun <K> LazyOptionScope.radioButtonIndexed(
    options: Map<K, String>,
    noinline selected: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionRadioButton(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        selected = selected(it, keys[it]),
        text = options[keys[it]]!!
    )
}

inline fun <K> LazyOptionScope.animatedRadioButton(
    options: Map<K, String>,
    noinline selected: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionRadioButton(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        selected = selected(keys[it]),
        text = options[keys[it]]!!
    )
}

inline fun <K> LazyOptionScope.animatedRadioButtonIndexed(
    options: Map<K, String>,
    noinline selected: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionRadioButton(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        selected = selected(it, keys[it]),
        text = options[keys[it]]!!
    )
}