package io.github.lanlacope.compose.ui.lazy.pager.helper

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import io.github.lanlacope.compose.composeable.ui.click.BoxButton
import io.github.lanlacope.compose.ui.busy.BusyRow
import io.github.lanlacope.compose.ui.busy.itemsIndexed
import kotlinx.coroutines.launch

private const val ITEMS_FIRST = 0


@Composable
fun <T> PagerHelper(
    items: List<T>,
    state: LazyListState,
    modifier: Modifier = Modifier,
    colors: PagerHelperColors = PagerHelperDefaults.colors()
) {
    val nowIndex by remember { derivedStateOf { state.firstVisibleItemIndex } }

    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    var contentsWidth by remember { mutableStateOf(0.dp) }

    BusyRow(
        modifier = modifier.onGloballyPositioned {
            contentsWidth = with(density) { it.size.width.toDp() } / items.size
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        itemsIndexed(
            items = items,
        ) { index, item ->

            val contentColor = animateColorAsState(
                targetValue = if (index == nowIndex) colors.selectedContentColor else colors.unselectedContentColor,
                label = "Pager"
            )

            val textColor = animateColorAsState(
                targetValue = if (index == nowIndex) colors.selectedTextColor else colors.unselectedTextColor,
                label = "Pager"
            )

            BoxButton(
                contentAlignment = Alignment.Center,
                onClick = { scope.launch { state.animateScrollToItem(index) } },
                modifier = Modifier
                    .width(contentsWidth)
                    .fillParentMaxHeight()
                    .background(contentColor.value)
            ) {
                Text(
                    text = item.toString(),
                    color = textColor.value
                )
            }

        }
    }
}



@Composable
fun <T> PagerHelperIndexed(
    items: List<T>,
    state: LazyListState,
    modifier: Modifier = Modifier,
    colors: PagerHelperColors = PagerHelperDefaults.colors()
) {
    val nowIndex by remember { derivedStateOf { state.firstVisibleItemIndex } }

    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    var contentsWidth by remember { mutableStateOf(0.dp) }

    BusyRow(
        modifier = modifier.onGloballyPositioned {
            contentsWidth = with(density) { it.size.width.toDp() } / items.size
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        itemsIndexed(
            items = items,
        ) { index, _ ->

            val contentColor = animateColorAsState(
                targetValue = if (index == nowIndex) colors.selectedContentColor else colors.unselectedContentColor,
                label = "Pager"
            )

            val textColor = animateColorAsState(
                targetValue = if (index == nowIndex) colors.selectedTextColor else colors.unselectedTextColor,
                label = "Pager"
            )

            BoxButton(
                contentAlignment = Alignment.Center,
                onClick = { scope.launch { state.animateScrollToItem(index) } },
                modifier = Modifier
                    .width(contentsWidth)
                    .fillParentMaxHeight()
                    .background(contentColor.value)
            ) {
                Text(
                    text = "${index + 1}",
                    color = textColor.value
                )
            }

        }
    }
}
