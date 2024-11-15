package io.github.lanlacope.lanlacopetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.github.lanlacope.rewheel.composeable.ui.action.option.OptionRadioButton
import io.github.lanlacope.rewheel.composeable.ui.action.setting.SettingTextArea
import io.github.lanlacope.rewheel.composeable.ui.dialog.DialogBox
import io.github.lanlacope.lanlacopetest.ui.theme.WidgitTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun View() {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

        var isTrue by remember {
            mutableStateOf(false)
        }

        var isShow by remember {
            mutableStateOf(true)
        }

        SettingTextArea(
            onClick = {
                isShow = !isShow
            },
            text = "テスト",
            summary = "テストです",
            value = "値だよ",
            modifier = Modifier.fillMaxWidth()
        )

        if (isShow) {
            DialogBox(
                title = "テストっと",
                onConfirm = { /*TODO*/ },
                onCancel = { },
                confirmText = "ok",
                cancelText = "cancel"
            ) {
                OptionRadioButton(
                    onClick = {
                        isTrue = !isTrue
                    },
                    text = "テスト",
                    value = isTrue,
                    modifier = Modifier.fillMaxWidth()
                )

            }
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