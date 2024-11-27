package io.github.lanlacope.compose.ui.busy.option

import androidx.compose.ui.Modifier
import io.github.lanlacope.collection.collection.keyList
import io.github.lanlacope.compose.ui.action.option.OptionCheckBox

inline fun <K> BusyOptionScope.checkBox(
    options: Map<K, String>,
    noinline checked: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionCheckBox(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        checked = checked(keys[it]),
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.checkBoxIndexed(
    options: Map<K, String>,
    noinline checked: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionCheckBox(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        checked = checked(it, keys[it]),
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.animatedCheckBox(
    options: Map<K, String>,
    noinline checked: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionCheckBox(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        checked = checked(keys[it]),
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.animatedCheckBoxIndexed(
    options: Map<K, String>,
    noinline checked: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null }
) = items(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionCheckBox(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        checked = checked(it, keys[it]),
        text = options[keys[it]]!!
    )
}