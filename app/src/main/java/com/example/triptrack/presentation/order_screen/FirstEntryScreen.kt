package com.example.triptrack.presentation.order_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrack.R

@Composable
fun FirstEntryScreen(

) {

  val focusManager = LocalFocusManager.current

  val pattern = remember { Regex("[а-яА-Я\\s]*") }

  val painter =
    if(isSystemInDarkTheme())
      painterResource(id = R.drawable.gradient_black)
    else
      painterResource(id = R.drawable.gradient_blue)

  val textColor =
    if(isSystemInDarkTheme())
      colorResource(id = R.color.text_color)
    else
      colorResource(id = R.color.text_color_night)

  val nameValue = remember {
    mutableStateOf(TextFieldValue(""))
  }

  val secondNameValue = remember {
    mutableStateOf(TextFieldValue(""))
  }


  Column(
    modifier = Modifier
      .fillMaxSize()
      .paint(
        painter = painter,
        contentScale = ContentScale.Crop
      ),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    CustomTextField(
      field = nameValue,
      textColor = textColor,
      text = "Имя",
      focusManager = focusManager,
      pattern = pattern,
      imeActions = ImeAction.Next,
      onClickAction = true,
      length = 15,
      keyboardType = KeyboardType.Text
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomTextField(
      field = secondNameValue,
      textColor = textColor,
      text = "Фамилия",
      focusManager = focusManager,
      pattern = pattern,
      length = 15,
      keyboardType = KeyboardType.Text
    )

    TextButton(
      onClick = { /*TODO*/ },
      shape = RoundedCornerShape(20.dp),
      modifier = Modifier
        .padding(top = 50.dp)
        .size(width = 96.dp, height = 48.dp),
      elevation = ButtonDefaults.buttonElevation(
        defaultElevation = 2.dp,
        pressedElevation = 0.dp
      ),
      colors = ButtonDefaults.textButtonColors(
        containerColor = colorResource(id = R.color.button_color)
      )
    ) {
      Text(text = "Запустить", color = textColor)
    }
  }
}


@Composable
fun CustomTextField(
  field: MutableState<TextFieldValue>,
  textColor: Color,
  text: String = "",
  focusManager: FocusManager,
  pattern: Regex,
  imeActions: ImeAction = ImeAction.Done,
  onClickAction: Boolean = false,
  length: Int,
  keyboardType: KeyboardType
) {
  val keyboardController = LocalSoftwareKeyboardController.current
  val keyboardActions = if(onClickAction) {
    KeyboardActions(
      onNext = {
        focusManager.moveFocus(focusDirection = FocusDirection.Down)
      }
    )
  } else {
    KeyboardActions(
      onDone = {
        keyboardController?.hide()
      }
    )
  }

  OutlinedTextField(
    value = field.value,
    onValueChange = { it ->
      if(field.value.text.length < length && field.value.text.matches(pattern)) {
        field.value = it
      }
    },
    label = { Text(text = text) },
    colors = OutlinedTextFieldDefaults.colors(
      focusedTextColor = textColor,
      focusedBorderColor = Color.DarkGray,
      unfocusedBorderColor = Color.Gray
    ),
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType,
      imeAction = imeActions
    ),
    keyboardActions = keyboardActions
  )
}


@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
fun FirstEntryScreenPreview() {
  FirstEntryScreen()
}

//todo length

//todo тряска