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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.triptrack.R
import com.example.triptrack.presentation.component.CALENDAR.calendar
import com.example.triptrack.presentation.component.CALENDAR.day
import com.example.triptrack.presentation.component.CALENDAR.month
import com.example.triptrack.presentation.component.CALENDAR.year
import com.example.triptrack.ui.theme.fontRegular
import java.util.Calendar
import java.util.Date

object CALENDAR {
    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
}

@SuppressLint("SimpleDateFormat")
@Composable
fun DateSelection(
    context: Context,
    date: String,
    orderDateChange: (String) -> Unit,
) {
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, _year: Int, _month: Int, _day: Int ->
            val dayToString = if (_day < 10) "0$_day" else "$_day"
            val monthToString = if (_month + 1 < 10) "0${_month + 1}" else "${_month + 1}"
            orderDateChange("$dayToString/$monthToString/$_year")
        },
        year,
        month,
        day,
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White),
        ) {
            Column {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        modifier = Modifier
                            .padding(start = 30.dp)
                            .height(20.dp),
                        text = stringResource(R.string.order_date),
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = Color.LightGray,
                        fontFamily = fontRegular,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(20.dp)
                        .bottomBorder(
                            1.dp,
                            Color.Black,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = date,
                        modifier = Modifier
                            .padding(start = 30.dp, top = 10.dp)
                            .clickable {
                            },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    IconButton(
                        onClick = { datePickerDialog.show() },
                        modifier = Modifier.padding(end = 30.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.Black,
                        )
                    }
                }
            }
        }
    }
}
