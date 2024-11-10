package io.github.lanlacope.lanlacopetest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.lanlacope.widgit.composeable.ui.busy.BusyRow
import io.github.lanlacope.widgit.composeable.ui.busy.items

@Composable
private fun View() {
    val context = LocalContext.current
    val list = listOf("asdfg", "ertyu", "zxcvb").toMutableStateList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        BusyRow(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Start)
                .background(Color.Blue),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(
                items = list
            ) {
                Text(
                    text = it,
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