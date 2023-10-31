package com.example.triptrack.presentation.component

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
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
    context, { _: DatePicker, _year: Int, _month: Int, _day: Int ->
      date = "$_day/${_month + 1}/$_year"
    }, year, month, day
  )

  val sdf = SimpleDateFormat("dd/M/yyyy")
  var currentDate by remember { mutableStateOf(sdf.format(Date())) }

  Column(
    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color.White)
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
            ), horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = currentDate, modifier = Modifier
              .padding(start = 30.dp, top = 10.dp)
              .clickable {

              }, style = MaterialTheme.typography.bodyMedium
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
