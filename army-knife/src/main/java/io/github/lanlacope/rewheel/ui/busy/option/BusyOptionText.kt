package io.github.lanlacope.rewheel.ui.busy.option

import androidx.compose.ui.Modifier
import io.github.lanlacope.rewheel.ui.action.option.OptionTextButton
import io.github.lanlacope.rewheel.util.collection.keyList

inline fun <K> BusyOptionScope.texts(
    options: Map<K, String>,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null },
) = options(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionTextButton(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.textsIndexed(
    options: Map<K, String>,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null },
) = options(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionTextButton(
        modifier = Modifier.fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.animatedTexts(
    options: Map<K, String>,
    crossinline onClick: (option: K) -> Unit,
    noinline key: ((item: K) -> Any)? = null,
    crossinline contentType: (item: K) -> Any? = { null },
) = options(
    count = options.size,
    key = if (key != null) { index: Int -> key(options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionTextButton(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(keys[it]) },
        text = options[keys[it]]!!
    )
}

inline fun <K> BusyOptionScope.animatedTextsIndexed(
    options: Map<K, String>,
    crossinline onClick: (index: Int, option: K) -> Unit,
    noinline key: ((index: Int, item: K) -> Any)? = null,
    crossinline contentType: (index: Int, item: K) -> Any? = { _, _ -> null },
) = options(
    count = options.size,
    key = if (key != null) { index: Int -> key(index, options.keyList()[index]) } else null,
    contentType = { index: Int -> contentType(index, options.keyList()[index]) }
) {
    val keys = options.keyList()
    OptionTextButton(
        modifier = Modifier
            .animateItem()
            .fillParentMaxWidth(),
        onClick = { onClick(it, keys[it]) },
        text = options[keys[it]]!!
    )
}