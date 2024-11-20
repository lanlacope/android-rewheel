package io.github.lanlacope.lanlacopetest.example.busy.option

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.composeable.ui.busy.option.BusyOption
import io.github.lanlacope.rewheel.composeable.ui.busy.option.checkBox
import io.github.lanlacope.rewheel.function.toggle

@Composable
internal fun ExampleBusyOption() {

    val options = remember { mutableStateMapOf("A" to "AAA", "B" to "BBB", "C" to "CCC") }
    val checked = remember { mutableStateListOf("A") }

    BusyOption(modifier = Modifier.fillMaxWidth()) {
        checkBox(
            options = options,
            checked = { checked.contains(it) },
            onClick = { checked.toggle(it) }
        )

    }
}

@Preview
@Composable
private fun BusyOptionPreview() {
    WidgitTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ExampleBusyOption()
        }
    }
}