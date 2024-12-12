package io.github.lanlacope.compose.ui.button.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.lanlacope.compose.composeable.ui.click.BoxButton

@Composable
fun ManuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ManuButtonColors = ManuButtonDefaults.colors(),
    content: @Composable ColumnScope.() -> Unit,
) {
    BoxButton(
        contentAlignment = Alignment.CenterStart,
        onClick = onClick,
        innerPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
        modifier = modifier
            .padding(all = 10.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(BorderStroke(1.dp, colors.borderColor), RoundedCornerShape(4.dp))
            .background(colors.containerColor)

    ) {
        Column {
            Row {
                Text(
                    text = text,
                    style = TextStyle(color = colors.textColor),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    tint = colors.textColor,
                    contentDescription = "contentDescription",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            content()
        }
    }
}