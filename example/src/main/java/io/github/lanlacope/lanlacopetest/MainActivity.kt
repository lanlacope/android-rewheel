package io.github.lanlacope.lanlacopetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.contextmenu.modifier.appendTextContextMenuComponents
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.lanlacope.rewheel.util.rememberCacheable
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WidgitTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                }
            }
        }
    }
}

@Composable
fun AppTest() {

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Hello World!",
            modifier = Modifier
                .fillMaxWidth()
        )

        var text by remember { mutableStateOf("Hello World!") }

        TextField(
            value = text,
            onValueChange = { text = it } ,
            modifier = Modifier
                .fillMaxWidth()
                .appendTextContextMenuComponents(
                    onCopy = { text },
                    onCut = { text },
                    onPaste = { text },
                    onSelectAll = { text }
                )
        )
    }
}
