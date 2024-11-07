package io.github.lanlacope.widgit.composeable.effect

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Suppress("unused")
@Composable
fun makeToast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT
): Toast {
    return Toast.makeText(LocalContext.current, text, duration)
}