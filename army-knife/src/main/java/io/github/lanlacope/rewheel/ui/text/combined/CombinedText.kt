package io.github.lanlacope.rewheel.ui.text.combined

import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit


@Composable
fun CombinedText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {

    var pressedRange by remember { mutableStateOf(IntRange.EMPTY) }
    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val baseText = remember(text) { text.convertCombinedLink() }
    val pressedStyle = remember(baseText) { text.getPressedStyle() }

    val displayText = remember(baseText, pressedRange) {
        baseText.applyPressedStyle(pressedRange, pressedStyle)
    }

    Text(
        text = displayText,
        modifier = modifier.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    if (layoutResult == null) return@awaitPointerEventScope
                    val down = awaitPointerEvent().changes.firstOrNull { it.pressed } ?: continue

                    val position = down.position
                    val offset = layoutResult!!.getOffsetForPosition(position).coerceIn(0, displayText.length - 1)
                    val box = layoutResult!!.getBoundingBox(offset)


                    pressedRange = if (box.contains(position)) {
                        displayText.getLinkRange(offset)
                    } else {
                        IntRange.EMPTY
                    }.apply { if (isEmpty()) continue }

                    down.consume()

                    val longPress =
                        withTimeoutOrNull(viewConfiguration.longPressTimeoutMillis) {
                            waitForUpOrCancellation()
                        }

                    if (longPress == null) {
                        text.onLongClick(pressedRange)
                    } else if (longPress.pressed.not()) {
                        text.onClick(pressedRange)
                    }

                    pressedRange = IntRange.EMPTY
                }
            }
        },
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = inlineContent,
        onTextLayout = {
            layoutResult = it
            onTextLayout(it)
        },
        style = style
    )
}