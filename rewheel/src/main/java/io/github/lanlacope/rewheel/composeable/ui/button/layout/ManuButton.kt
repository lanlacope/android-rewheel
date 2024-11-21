package io.github.lanlacope.rewheel.composeable.ui.button.layout

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.lanlacope.rewheel.composeable.ui.click.BoxButton

@Composable
fun ManuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    content: @Composable ColumnScope.() -> Unit
) {
    BoxButton(
        contentAlignment = Alignment.CenterStart,
        onClick = onClick,
        innerPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
        modifier = modifier
            .padding(all = 10.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(4.dp))

    ) {
        Column {
            Row {
                Text(
                    text = text,
                    style = TextStyle(color = color),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    tint = color,
                    contentDescription = "contentDescription",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            content()
        }
    }
}