package io.github.lanlacope.lanlacopetest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopelib.composeable.ui.lazy.pager.LazyHorizontalPager
import io.github.lanlacope.lanlacopelib.composeable.ui.lazy.pager.items

@Composable
private fun View() {
    val list = listOf("asdfg", "ertyu", "zxcvb").toMutableStateList()

    LazyHorizontalPager {
        items(
            list,{it}
        ) {
            Text(text = it)
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