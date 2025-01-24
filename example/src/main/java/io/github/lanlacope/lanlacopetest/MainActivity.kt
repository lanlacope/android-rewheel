package io.github.lanlacope.lanlacopetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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

    Column(modifier = Modifier.fillMaxSize()) {

        var boolA by rememberCacheable("TEST") {
            mutableStateOf(false)
        }

        Switch(
            checked = boolA,
            onCheckedChange = {
                boolA = !boolA
            }
        )

        var boolB by rememberCacheable("TEST_B") {
            mutableStateOf(false)
        }

        Switch(
            checked = boolB,
            onCheckedChange = {
                boolB = !boolB
            }
        )

        var boolC by rememberCacheable("TEST_C") {
            mutableStateOf(false)
        }

        Switch(
            checked = boolC,
            onCheckedChange = {
                boolC = !boolC
            }
        )
    }
}
