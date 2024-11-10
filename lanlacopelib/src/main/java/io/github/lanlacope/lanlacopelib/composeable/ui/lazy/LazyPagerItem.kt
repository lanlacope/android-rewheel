package io.github.lanlacope.lanlacopelib.composeable.ui.lazy

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("unused")
inline fun <T> LazyListScope.pagerItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) { index ->
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillParentMaxSize()
    ) {
        itemContent(items[index])
    }
}

@Suppress("unused")
inline fun <T> LazyListScope.animatedPagerItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) { index ->
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .animateItem()
            .fillParentMaxSize()
    ) {
        itemContent(items[index])
    }
}
