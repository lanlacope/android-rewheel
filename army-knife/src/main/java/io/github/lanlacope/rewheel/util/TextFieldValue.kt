package io.github.lanlacope.rewheel.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getTextAfterSelection
import androidx.compose.ui.text.input.getTextBeforeSelection

fun TextFieldValue.isSelectedAll(): Boolean {
    return selection.min == 0 && selection.max == text.length
}

fun TextFieldValue.isNotSelectedAll(): Boolean {
    return !isSelectedAll()
}

fun TextFieldValue.isSelectedNone(): Boolean {
    return selection.min == selection.max
}

fun TextFieldValue.isNotSelectedNone(): Boolean {
    return !isSelectedNone()
}

fun TextFieldValue.getTextBeforeSelectionPrefix(): AnnotatedString {
    return getTextBeforeSelection(selection.min)
}

fun TextFieldValue.getTextAfterSelectionSuffix(): AnnotatedString {
    return getTextAfterSelection(text.length - selection.max)
}

fun TextFieldValue.insertText(text: AnnotatedString): TextFieldValue {
    val newText = buildAnnotatedString {
        append(getTextBeforeSelectionPrefix())
        append(text)
        append(getTextAfterSelectionSuffix())
    }
    val newCursor = selection.min + text.text.length
    return this.copy(annotatedString = newText, selection = TextRange(newCursor, newCursor))
}

fun TextFieldValue.removeSelectedText(): TextFieldValue {
    // return if empty
    if (isSelectedNone()) return this

    val newText = buildAnnotatedString {
        append(getTextBeforeSelectionPrefix())
        append(getTextAfterSelectionSuffix())
    }
    return this.copy(annotatedString = newText, selection = TextRange(selection.min, selection.min))
}

fun TextFieldValue.selectAll(): TextFieldValue = this.copy(selection = TextRange(0, text.length))