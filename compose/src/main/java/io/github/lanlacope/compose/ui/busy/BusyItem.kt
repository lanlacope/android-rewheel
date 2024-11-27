package io.github.lanlacope.compose.ui.busy

import androidx.annotation.FloatRange
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import io.github.lanlacope.compose.composeable.ui.click.BoxButton

/*
 * items
 */
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


/*
 * clickableItems
 */
inline fun <T> BusyListScope.items(
    items: List<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(onClick = { onClick(items[it]) }) {
        itemContent(items[it])
    }
}

inline fun <T> BusyListScope.itemsIndexed(
    items: List<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    BoxButton(onClick = { onClick(it, items[it]) }) {
        itemContent(it, items[it])
    }
}

inline fun <T> BusyListScope.items(
    items: Array<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(onClick = { onClick(items[it]) }) {
        itemContent(items[it])
    }
}

inline fun <T> BusyListScope.itemsIndexed(
    items: Array<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
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
inline fun <T> BusyListScope.animatedItems(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(modifier = Modifier.animateItem()) {
        itemContent(items[it])
    }
}

inline fun <T> BusyListScope.animatedItemsIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    Box(modifier = Modifier.animateItem()) {
        itemContent(it, items[it])
    }
}

inline fun <T> BusyListScope.animatedItems(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(modifier = Modifier.animateItem()) {
        itemContent(items[it])
    }
}

inline fun <T> BusyListScope.animatedItemsIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
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
 * aniatedClickableItems
 */
inline fun <T> BusyListScope.animatedItems(
    items: List<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(modifier = Modifier.animateItem(),
        onClick = { onClick(items[it]) }) {
        itemContent(items[it])
    }
}

inline fun <T> BusyListScope.animatedItemsIndexed(
    items: List<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    BoxButton(modifier = Modifier.animateItem(),
        onClick = { onClick(it, items[it]) }) {
        itemContent(it, items[it])
    }
}

inline fun <T> BusyListScope.animatedItems(
    items: Array<T>,
    crossinline onClick: ((item: T) -> Unit),
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable BusyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    BoxButton(modifier = Modifier.animateItem(),
        onClick = { onClick(items[it]) }) {
        itemContent(items[it])
    }
}

inline fun <T> BusyListScope.animatedItemsIndexed(
    items: Array<T>,
    crossinline onClick: ((index: Int, item: T) -> Unit),
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable BusyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    BoxButton(modifier = Modifier.animateItem(),
        onClick = { onClick(it, items[it]) }) {
        itemContent(it, items[it])
    }
}

@Stable
@LazyScopeMarker
interface BusyItemScope {

    fun Modifier.fillParentMaxSize(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.fillParentMaxWidth(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.fillParentMaxHeight(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.animateItem(
        fadeInSpec: FiniteAnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        placementSpec: FiniteAnimationSpec<IntOffset>? = spring(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        ),
        fadeOutSpec: FiniteAnimationSpec<Float>? =
            spring(stiffness = Spring.StiffnessMediumLow),
    ): Modifier = this
}


internal class BusyItemScopeImpl : BusyItemScope {
    override fun Modifier.fillParentMaxSize(fraction: Float): Modifier {
        return this.fillMaxSize(fraction)
    }

    override fun Modifier.fillParentMaxWidth(fraction: Float): Modifier {
        return this.fillMaxWidth(fraction)
    }

    override fun Modifier.fillParentMaxHeight(fraction: Float): Modifier {
        return this.fillMaxHeight(fraction)
    }
}