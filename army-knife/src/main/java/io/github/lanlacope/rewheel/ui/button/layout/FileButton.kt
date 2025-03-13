package io.github.lanlacope.rewheel.ui.button.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.lanlacope.rewheel.composeable.ui.click.BoxButton
import io.github.lanlacope.rewheel.ui.animation.DrawUpAnimated
import io.github.lanlacope.rewheel.ui.animation.FadeInAnimated

@Composable
fun FileButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorText: String? = null,
    isError: Boolean = false,
    colors: FileButtonColors = FileButtonDefaults.colors(),
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BoxButton(
                contentAlignment = Alignment.CenterStart,
                onClick = onClick,
                innerPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        BorderStroke(
                            1.dp,
                            colors.borderColor
                        ), RoundedCornerShape(4.dp)
                    )
                    .background(colors.containerColor)

            ) {
                Text(
                    text = text,
                    style = TextStyle(color = colors.textColor),
                )

            }
            FadeInAnimated(visible = isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = colors.indicatorColor,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
        if (!errorText.isNullOrEmpty())
            DrawUpAnimated(visible = isError) {
                Row(modifier = Modifier.padding(start = 10.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = Icons.Filled.Info.name,
                        tint = colors.errorMessageColor
                    )
                    Text(
                        text = errorText,
                        color = colors.errorMessageColor
                    )
                }
            }
    }
}