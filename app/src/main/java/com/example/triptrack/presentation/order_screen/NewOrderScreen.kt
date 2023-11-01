package com.example.triptrack.presentation.order_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrack.R
import com.example.triptrack.presentation.component.AutoComplete
import com.example.triptrack.presentation.component.CheckboxTooltip
import com.example.triptrack.presentation.component.DateSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOrderScreen(

) {

  val context = LocalContext.current

  val checkedState = remember { mutableStateOf(true) }

  val painter = if(isSystemInDarkTheme()) painterResource(id = R.drawable.gradient_black)
  else painterResource(id = R.drawable.gradient_blue)

  val textColor = if(isSystemInDarkTheme()) colorResource(id = R.color.text_color)
  else colorResource(id = R.color.text_color_night)

  val routeValue = remember {
    mutableStateOf(TextFieldValue(""))
  }

  val secondNameValue = remember {
    mutableStateOf(TextFieldValue(""))
  }
  Scaffold(topBar = {
    TopAppBar(title = {
      Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
      ) { Text("Новый заказ") }
    }, navigationIcon = {
      Icon(
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        contentDescription = null,
        modifier = Modifier.size(30.dp)
      )
    })
  }) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .paint(
          painter = painter, contentScale = ContentScale.Crop
        ), verticalArrangement = Arrangement.Top
    ) {

      DateSelection(context)

      Spacer(modifier = Modifier.height(20.dp))

      NewOrderCard(checkedState)

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp, start = 35.dp, end = 35.dp)
          .height(40.dp)
          .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
      ) {
        Text(modifier = Modifier.padding(start = 20.dp), text = "Итого: ${1000 - 7}")
      }
      TextButton(
        onClick = { /*TODO*/ },

        modifier = Modifier

          .padding(top = 20.dp)
          .size(
            width = 120.dp,
            height = 48.dp
          )
          .align(Alignment.CenterHorizontally),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.buttonElevation(
          defaultElevation = 2.dp,
          pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.textButtonColors(
          containerColor = colorResource(id = R.color.button_color)
        )
      ) {
        Text(text = "Завершить", color = textColor)
      }


    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOrderCard(checked: MutableState<Boolean>) {
  val moneyValuesUp = listOf("+3000", "+1000", "+500")
  val moneyValuesDown = listOf("-2000", "-1000", "-500")
  val tooltipState = rememberTooltipState(isPersistent = true)
  val categories = listOf(
    "Food", "Beverages", "Sports", "Learning", "Travel", "Rent", "Bills", "Fees", "Others"
  )
  Box(
    modifier = Modifier
      .height(470.dp)
      .fillMaxWidth()
      .padding(start = 25.dp, end = 25.dp)
      .clip(RoundedCornerShape(25.dp))
      .background(color = Color.White)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 130.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      CheckboxTooltip(tooltipState = tooltipState, checked = checked, text = "Налог")
      CheckboxTooltip(tooltipState = tooltipState, checked = checked, text = "Оплата")
    }
    AutoComplete(categories, "Заказчик")

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 210.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Surface(
        shadowElevation = 5.dp
      ) {
        OutlinedTextField(
          value = "1",
          onValueChange = {}
        )
      }
      Spacer(modifier = Modifier.height(20.dp))
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        repeat(3) {
          Surface(shadowElevation = 5.dp) {
            ButtonWithMoneyValue(moneyValuesUp[it])
          }
        }
      }
      Spacer(modifier = Modifier.height(15.dp))
      OutlinedTextField(
        value = "1",
        onValueChange = {},
      )
      Spacer(modifier = Modifier.height(20.dp))
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        repeat(3) {
          Surface(shadowElevation = 5.dp) {
            ButtonWithMoneyValue(moneyValuesDown[it])
          }
        }
      }
    }
  }


}


@Composable
@Preview(showBackground = true)
fun NewOrderScreenPreview() {
  val context = LocalContext.current
  NewOrderScreen()
}

@Composable
fun ButtonWithMoneyValue(text: String) {
  Button(
    shape = RoundedCornerShape(5.dp),
    onClick = { /*TODO*/ },
    modifier = Modifier
      .border(
        width = 1.dp,
        color = colorResource(
          id = R.color.tool_tip
        )
      )
      .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(bounded = false),
        onClick = {}
      )
      .height(35.dp)
      .animateContentSize(),
    colors = ButtonColors(
      containerColor = Color.Transparent,
      contentColor = Color.Black,
      disabledContainerColor = Color.Transparent,
      disabledContentColor = Color.Black
    )
  ) {
    Text(text = text)
  }
}






