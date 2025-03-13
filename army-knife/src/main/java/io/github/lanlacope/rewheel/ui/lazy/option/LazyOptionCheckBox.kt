package io.github.lanlacope.rewheel.ui.lazy.option

import androidx.compose.ui.Modifier
import io.github.lanlacope.rewheel.ui.action.option.OptionCheckBox
import io.github.lanlacope.rewheel.util.collection.keyList

inline fun <K> LazyOptionScope.checkBoxes(
    options: Map<K, String>,
    noinline checked: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null },
) = options(
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

inline fun <K> LazyOptionScope.checkBoxesIndexed(
    options: Map<K, String>,
    noinline checked: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null },
) = options(
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

inline fun <K> LazyOptionScope.animatedCheckBoxes(
    options: Map<K, String>,
    noinline checked: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null },
) = options(
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

inline fun <K> LazyOptionScope.animatedCheckBoxesIndexed(
    options: Map<K, String>,
    noinline checked: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null },
) = options(
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