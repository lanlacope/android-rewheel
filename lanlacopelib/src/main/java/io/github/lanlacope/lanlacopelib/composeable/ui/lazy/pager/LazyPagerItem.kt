package io.github.lanlacope.lanlacopelib.composeable.ui.lazy.pager

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

inline fun <T> LazyListScope.pagerItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillParentMaxSize()
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.pagerItemsIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillParentMaxSize()
    ) {
        itemContent(it, items[it])
    }
}

inline fun <T> LazyListScope.pagerItems(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillParentMaxSize()
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.pagerItemsIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillParentMaxSize()
    ) {
        itemContent(it, items[it])
    }
}


inline fun <T> LazyListScope.animatedPagerItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.animatedPagerItemsIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(it, items[it])
    }
}

inline fun <T> LazyListScope.animatedPagerItems(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyListScope.animatedPagerItemsIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(it, items[it])
    }
}