package io.github.lanlacope.rewheel.ui.lazy.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
 * items
 */
inline fun <T> LazyPagerScope.pages(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(modifier = Modifier.fillParentMaxSize()) {
        itemContent(items[it])
    }
}

inline fun <T> LazyPagerScope.pagesIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    Box(modifier = Modifier.fillParentMaxSize()) {
        itemContent(it, items[it])
    }
}

inline fun <T> LazyPagerScope.pages(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(modifier = Modifier.fillParentMaxSize()) {
        itemContent(items[it])
    }
}

inline fun <T> LazyPagerScope.pagesIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    Box(modifier = Modifier.fillParentMaxSize()) {
        itemContent(it, items[it])
    }
}


/*
 * animatedItems
 */
inline fun <T> LazyPagerScope.animatedPages(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyPagerScope.animatedPagesIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(it, items[it])
    }
}

inline fun <T> LazyPagerScope.animatedPages(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(items[it])
    }
}

inline fun <T> LazyPagerScope.animatedPagesIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit,
) = pages(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(it, items[it])
    }
}