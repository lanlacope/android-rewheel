package io.github.lanlacope.widgit.composeable.ui.busy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.layout.Layout

@Composable
fun ColumnScope.Item(
    key: Any? = null,
    contentType: Any? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(content = content)
}

@Composable
fun ColumnScope.Items(
    count: Int,
    key: ((index: Int) -> Any)? = null,
    contentType: (index: Int) -> Any? = { null },
    itemContent: @Composable BoxScope.(index: Int) -> Unit
) {
    for (index in 0 until  count) {
        Item(
            key = key?.invoke(index),
            contentType = contentType(index)
        ) {
            itemContent(index)
        }
    }
}

@Composable
inline fun <T> ColumnScope.Items(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BoxScope.(item: T) -> Unit
) = Items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}

@Composable
inline fun <T> ColumnScope.ItemsIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BoxScope.(index: Int, item: T) -> Unit
) = Items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    itemContent(it, items[it])
}

@Composable
inline fun <T> ColumnScope.Items(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BoxScope.(item: T) -> Unit
) = Items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}

@Composable
inline fun <T> ColumnScope.ItemsIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BoxScope.(index: Int, item: T) -> Unit
) = Items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    itemContent(it, items[it])
}