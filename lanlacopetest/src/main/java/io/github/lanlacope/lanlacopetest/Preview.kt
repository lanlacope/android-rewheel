package io.github.lanlacope.lanlacopetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopelib.composeable.ui.lazy.pager.LazyHorizontalPager
import io.github.lanlacope.lanlacopelib.composeable.ui.lazy.pager.items
import io.github.lanlacope.rewheel.composeable.ui.busy.BusyColumn
import io.github.lanlacope.rewheel.composeable.ui.busy.items
import io.github.lanlacope.rewheel.composeable.ui.click.Box
import kotlin.math.truncate

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun View() {
    val list = List(100) { it + 1 }.toMutableStateList()

    LazyHorizontalPager {

        items(
            items = list,
            key = {it}
        ) {
           androidx.compose.foundation.layout.Box(
               modifier = Modifier
                   .background(Color.Gray)
           ) {
               Text(
                   text = it.toString(),
                   modifier = Modifier
                       .align(Alignment.Center)

               )
           }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        View()
    }
}