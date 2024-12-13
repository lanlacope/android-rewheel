package io.github.lanlacope.compose.ui.lazy.pager.helper

import androidx.annotation.IntRange
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.lanlacope.compose.composeable.ui.click.BoxButton
import io.github.lanlacope.compose.ui.busy.BusyRow
import io.github.lanlacope.compose.ui.busy.itemsIndexed
import kotlinx.coroutines.launch

private const val ITEMS_FIRST = 0

@Composable
fun <T> PagerIndexHelper(
    items: List<T>,
    state: LazyListState,
    modifier: Modifier = Modifier,
    @IntRange(from = 0)
    centerRange: Int = 1,
    colors: PagerHelperColors = PagerHelperDefaults.colors()
) {
    val nowIndex by remember { derivedStateOf { state.firstVisibleItemIndex } }

    val scope = rememberCoroutineScope()

    val adjustedRange = remember(nowIndex) {
        val centerMin =((nowIndex - centerRange).coerceAtLeast(ITEMS_FIRST + 1))
        val centerMax = (nowIndex + centerRange).coerceAtMost(items.lastIndex - 1)

        val lowerBound = (nowIndex - centerRange).coerceAtLeast(centerMin)
        val upperBound = (nowIndex + centerRange).coerceAtMost(centerMax)

        when {
            lowerBound == ITEMS_FIRST + 1 -> {
                lowerBound..(1 + centerRange * 2).coerceAtMost(items.lastIndex - 1)
            }
            upperBound == items.lastIndex - 1 -> {
                (upperBound - centerRange * 2).coerceAtLeast(ITEMS_FIRST + 1)..upperBound
            }
            else -> lowerBound..upperBound
        }
    }

    BusyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        itemsIndexed(
            items = items,
        ) { index, _ ->

            if (index == ITEMS_FIRST || index == items.lastIndex
                || index in adjustedRange) {

                val contentColor = animateColorAsState(
                    targetValue = if (index == nowIndex) colors.selectedContentColor else colors.unselectedContentColor,
                    label = "Pager"
                )

                val textColor = animateColorAsState(
                    targetValue = if (index == nowIndex) colors.selectedTextColor else colors.unselectedTextColor,
                    label = "Pager"
                )

                val centerSize = animateDpAsState(
                    targetValue = if (index == nowIndex) 50.dp else 30.dp,
                    label = "Pager"
                )

                BoxButton(
                    contentAlignment = Alignment.Center,
                    onClick = { scope.launch { state.animateScrollToItem(index) } },
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .size(centerSize.value)
                        .background(contentColor.value)
                ) {
                    Text(
                        text = "${index + 1}",
                        color = textColor.value)
                }
            }
        }
    }
}
