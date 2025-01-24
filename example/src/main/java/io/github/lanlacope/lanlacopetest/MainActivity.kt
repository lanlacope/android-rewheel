package io.github.lanlacope.lanlacopetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.lanlacope.compose.unit.rememberCacheable
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WidgitTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppTest()
                }
            }
        }
    }
}

@Composable
fun AppTest() {

    var bool by rememberCacheable("TESTD") {
        mutableStateOf(false)
    }

    Switch(
        checked = bool,
        onCheckedChange = {
            bool = !bool
        }
    )
}
