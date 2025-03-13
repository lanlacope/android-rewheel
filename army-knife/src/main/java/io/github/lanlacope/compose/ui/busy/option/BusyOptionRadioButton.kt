package io.github.lanlacope.compose.ui.busy.option

import androidx.compose.ui.Modifier
import io.github.lanlacope.compose.ui.action.option.OptionRadioButton
import io.github.lanlacope.compose.util.collection.keyList

inline fun <K> BusyOptionScope.radioButtons(
    options: Map<K, String>,
    noinline selected: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null },
) = options(
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

inline fun <K> BusyOptionScope.radioButtonsIndexed(
    options: Map<K, String>,
    noinline selected: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null },
) = options(
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

inline fun <K> BusyOptionScope.animatedRadioButtons(
    options: Map<K, String>,
    noinline selected: (option: K) -> Boolean,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null },
) = options(
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

inline fun <K> BusyOptionScope.animatedRadioButtonsIndexed(
    options: Map<K, String>,
    noinline selected: (index: Int, option: K) -> Boolean,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null },
) = options(
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