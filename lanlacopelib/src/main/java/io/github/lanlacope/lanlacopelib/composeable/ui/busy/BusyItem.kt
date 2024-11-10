package io.github.lanlacope.widgit.composeable.ui.busy

import androidx.annotation.FloatRange
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset

inline fun <T> BusyListScope.items(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}

inline fun <T> BusyListScope.itemsIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    itemContent(it, items[it])
}

inline fun <T> BusyListScope.items(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}

inline fun <T> BusyListScope.itemsIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    itemContent(it, items[it])
}
