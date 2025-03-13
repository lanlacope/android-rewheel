package io.github.lanlacope.rewheel.ui.lazy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lanlacope.rewheel.composeable.ui.click.BoxButton

/*
 * clickableItems
 */
inline fun <T> LazyListScope.items(
    items: List<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(onClick = { onClick(items[it]) }) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.itemsIndexed(
    items: List<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    BoxButton(onClick = { onClick(it, items[it]) }) {
        itemContent(it, items[it])
    }
}

inline fun <T> LazyListScope.items(
    items: Array<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(onClick = { onClick(items[it]) }) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.itemsIndexed(
    items: Array<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    BoxButton(onClick = { onClick(it, items[it]) }) {
        itemContent(it, items[it])
    }
}


/*
 * animatedItems
 */
inline fun <T> LazyListScope.animatedItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(modifier = Modifier.animateItem()) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.animatedItemsIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    Box(modifier = Modifier.animateItem()) {
        itemContent(it, items[it])
    }
}

inline fun <T> LazyListScope.animatedItems(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(modifier = Modifier.animateItem()) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.animatedItemsIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    Box(modifier = Modifier.animateItem()) {
        itemContent(it, items[it])
    }
}

/*
 * animatedClickableItems
 */
inline fun <T> LazyListScope.animatedItems(
    items: List<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(
        modifier = Modifier.animateItem(),
        onClick = { onClick(items[it]) }
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.animatedItemsIndexed(
    items: List<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    BoxButton(
        modifier = Modifier.animateItem(),
        onClick = { onClick(it, items[it]) }
    ) {
        itemContent(it, items[it])
    }
}

inline fun <T> LazyListScope.animatedItems(
    items: Array<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(
        modifier = Modifier.animateItem(),
        onClick = { onClick(items[it]) }
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.animatedItemsIndexed(
    items: Array<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    BoxButton(
        modifier = Modifier.animateItem(),
        onClick = { onClick(it, items[it]) }
    ) {
        itemContent(it, items[it])
    }
}