package com.example.triptrack.presentation.order_screen

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.triptrack.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOrderScreen(

) {

  val context = LocalContext.current

  val checkedState = remember { mutableStateOf(true) }

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

  val routeValue = remember {
    mutableStateOf(TextFieldValue(""))
  }

  val secondNameValue = remember {
    mutableStateOf(TextFieldValue(""))
  }
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
          ) { Text("Новый заказ") }
        },
        navigationIcon = {
          Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
          )
        }
      )
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .paint(
          painter = painter,
          contentScale = ContentScale.Crop
        ),
      verticalArrangement = Arrangement.Top
    ) {

      DateSelection(context)

      Spacer(modifier = Modifier.height(30.dp))

      NewOrderCard(checkedState)

    }
  }
}

@Composable
fun NewOrderCard(checked: MutableState<Boolean>) {
  val categories = listOf(
    "Food",
    "Beverages",
    "Sports",
    "Learning",
    "Travel",
    "Rent",
    "Bills",
    "Fees",
    "Others"
  )
  Surface(
    modifier = Modifier
      .height(200.dp)
      .fillMaxWidth()
      .padding(start = 25.dp, end = 25.dp),
    color = Color.White,
    shape = RoundedCornerShape(25.dp)
  ) {
    Column {
      AutoComplete(categories, "Заказчик")
      Row(modifier = Modifier.fillMaxSize()) {
        Checkbox(
          checked = checked.value,
          onCheckedChange = { checked_ ->
            checked.value = checked_
          },
          colors = CheckboxDefaults.colors(
            checkedColor = colorResource(id = R.color.check_box)
          )
        )
      }
    }
  }
}


//    TextButton(
//      onClick = { /*TODO*/ },
//      shape = RoundedCornerShape(20.dp),
//      modifier = Modifier
//        .padding(top = 50.dp)
//        .size(width = 96.dp, height = 48.dp),
//      elevation = ButtonDefaults.buttonElevation(
//        defaultElevation = 2.dp,
//        pressedElevation = 0.dp
//      ),
//      colors = ButtonDefaults.textButtonColors(
//        containerColor = colorResource(id = R.color.button_color)
//      )
//    ) {
//      Text(text = "Завершить", color = textColor)
//    }


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
fun NewOrderScreenPreview() {
  val context = LocalContext.current
  NewOrderScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoComplete(
  values: List<String>,
  text: String
) {

  var category by remember {
    mutableStateOf("")
  }

  val heightTextFields by remember {
    mutableStateOf(40.dp)
  }

  var textFieldSize by remember {
    mutableStateOf(Size.Zero)
  }

  var expanded by remember {
    mutableStateOf(false)
  }

  val cardColor =
    if(isSystemInDarkTheme()) colorResource(id = R.color.auto_complete_light)
    else colorResource(id = R.color.auto_complete_night)

  Column(
    modifier = Modifier
      .padding(30.dp)
      .fillMaxWidth()
      .clickable(
        onClick = {
          expanded = false
        }
      )

  ) {

    Text(
      modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
      text = text,
      fontSize = 16.sp,
      color = Color.Black,
      fontWeight = FontWeight.Medium
    )

    Column(modifier = Modifier.fillMaxWidth()) {

      Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
          modifier = Modifier
            .fillMaxWidth()
            .height(heightTextFields)
            .border(
              width = 1.dp,
              color = Color.Black,
              shape = RoundedCornerShape(20.dp)
            )
            .onGloballyPositioned { coordinates ->
              textFieldSize = coordinates.size.toSize()
            },
          value = category,
          onValueChange = {
            category = it
            expanded = true
          },
          colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
          ),
          textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
          ),
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
          ),
          singleLine = true,
          trailingIcon = {
            IconButton(onClick = { expanded = !expanded }) {
              Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "arrow",
                tint = Color.Black
              )
            }
          }
        )
      }

      AnimatedVisibility(visible = expanded) {
        Card(
          modifier = Modifier
            .padding(horizontal = 5.dp)
            .width(textFieldSize.width.dp),
          elevation = CardDefaults.cardElevation(15.dp),
          shape = RoundedCornerShape(10.dp),
          colors = CardDefaults.cardColors(cardColor)
        ) {

          LazyColumn(
            modifier = Modifier
              .heightIn(max = 150.dp)
              .background(Color.Transparent),

            ) {

            if(category.isNotEmpty()) {
              items(
                values.filter {
                  it.lowercase().contains(category.lowercase()) || it.lowercase().contains("others")
                }.sorted()
              ) {
                CategoryItems(title = it) { title ->
                  category = title
                  expanded = false
                }
              }
            } else {
              items(
                values.sorted()
              ) {
                CategoryItems(title = it) { title ->
                  category = title
                  expanded = false
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun CategoryItems(
  title: String,
  onSelect: (String) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable {
        onSelect(title)
      }
      .padding(10.dp)
      .background(Color.Transparent)
  ) {
    Text(
      text = title,
      fontSize = 16.sp,
      fontStyle = FontStyle.Italic
    )
  }

}


@Composable
fun DateSelection(context: Context) {

  val calendar = Calendar.getInstance()
  val year: Int = calendar.get(Calendar.YEAR)
  val month: Int = calendar.get(Calendar.MONTH)
  val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
  calendar.time = Date()
  var date by remember {
    mutableStateOf("")
  }
  val datePickerDialog = DatePickerDialog(
    context,
    { _: DatePicker, _year: Int, _month: Int, _day: Int ->
      date = "$_day/${_month + 1}/$_year"
    }, year, month, day
  )

  val sdf = SimpleDateFormat("dd/M/yyyy")
  var currentDate by remember { mutableStateOf(sdf.format(Date())) }

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Surface(
      modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(Color.LightGray),
      shadowElevation = 5.dp,
    ) {
      Column {
        Row(verticalAlignment = Alignment.Bottom) {
          Text(
            modifier = Modifier
              .padding(start = 30.dp)
              .height(20.dp),
            text = "Дата заказа",
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            color = Color.LightGray
          )
        }

        Row(
          modifier = Modifier
            .fillMaxSize()
            .height(20.dp)
            .bottomBorder(
              1.dp, Color.Black
            ),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = currentDate,
            modifier = Modifier
              .padding(start = 30.dp, top = 10.dp)
              .clickable{

              },
            style = MaterialTheme.typography.bodyMedium
          )
          IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 30.dp)) {
            Icon(
              imageVector = Icons.Default.DateRange,
              contentDescription = null,
              tint = Color.Black
            )
          }
        }
      }
    }
  }
}


fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
  factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }
    Modifier.drawBehind {
      val width = size.width
      val height = size.height - strokeWidthPx / 2

      drawLine(
        color = color,
        start = Offset(x = 0f, y = height),
        end = Offset(x = width, y = height),
        strokeWidth = strokeWidthPx
      )
    }
  }
)
