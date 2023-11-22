package com.example.triptrack.presentation.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.triptrack.ui.theme.fontItalic

@Composable
fun CustomTextField(
    field: String,
    fieldChange: (String) -> Unit,
    textColor: Color,
    text: String = "",
    focusManager: FocusManager,
    imeActions: ImeAction = ImeAction.Done,
    onClickAction: Boolean = false,
    keyboardType: KeyboardType,
    isError: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardActions = if (onClickAction) {
        KeyboardActions(
            onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            },
        )
    } else {
        KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
        )
    }

    OutlinedTextField(
        value = field,
        onValueChange = fieldChange,
        textStyle = TextStyle(fontFamily = fontItalic, fontSize = 20.sp),
        label = { Text(text = text) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.Gray,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeActions,
        ),
        keyboardActions = keyboardActions,
        isError = isError,
    )
}
