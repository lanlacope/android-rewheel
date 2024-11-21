package io.github.lanlacope.lanlacopetest.example.busy.option

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme
import io.github.lanlacope.rewheel.composeable.ui.busy.option.BusyOption
import io.github.lanlacope.rewheel.composeable.ui.busy.option.checkBox
import io.github.lanlacope.rewheel.composeable.ui.busy.option.radioButton
import io.github.lanlacope.rewheel.function.toggle

@Composable
internal fun ExampleBusyOption() {

    val checkBoxOptions = remember { mutableStateListOf("A", "B", "C") }
    val checkedOptions = remember { mutableStateListOf("A") }

    val radioButtonOptions = remember { mutableStateListOf(1, 2, 3) }
    var selectedOption by remember { mutableStateOf(1) }

    BusyOption(modifier = Modifier.fillMaxWidth()) {
        checkBox(
            options = checkBoxOptions.associateWith { "Option: $it" },
            checked = { checkedOptions.contains(it) },
            onClick = { checkedOptions.toggle(it) }
        )

        radioButton(
            options = radioButtonOptions.associateWith { "Option: $it" },
            selected = { selectedOption == it },
            onClick = { selectedOption = it }
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