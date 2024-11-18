package io.github.lanlacope.lanlacopetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.composeable.ui.action.option.OptionCheckBox
import io.github.lanlacope.rewheel.composeable.ui.lazy.option.LazyOption
import io.github.lanlacope.rewheel.composeable.ui.lazy.option.checkBox
import io.github.lanlacope.rewheel.composeable.ui.lazy.option.radioButton
import io.github.lanlacope.rewheel.function.toggle

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun View() {
    val context = LocalContext.current

    buildString {

    }

    Column {

        val tezt1 = "TEST1"
        var checked1 by remember {
            mutableStateOf(false)
        }

        OptionCheckBox(
            checked = checked1,
            onClick = {
                checked1 = !checked1
            },
            text = tezt1,
            modifier = Modifier.fillMaxWidth()
        )



        val listb = mutableListOf("あ", "3", "B")
        var selected by remember {
            mutableStateOf("あ")
        }
        LazyOption {
            radioButton(
                options = listb.associateWith {
                    when (it) {
                        "あ" -> "あですaaaabbbb"
                        "B" -> "Bでｓｔ"
                        "3" -> "３だよ"
                        else -> throw Exception()
                    }
                },
                selected = { it == selected},
                onClick = { option ->
                    selected = option
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            View()
        }
    }
}