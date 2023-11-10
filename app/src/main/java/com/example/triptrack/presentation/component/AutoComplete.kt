package com.example.triptrack.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.R

@Composable
fun AutoComplete(
    selectedEmployer: String,
    selectedEmployerUpdate: (String) -> Unit,
    expanded: Boolean,
    expandedUpdate: (Boolean) -> Unit,
    employers: Set<String>,
    text: String,
    textFieldSize: Size,
    textFieldSizeUpdate: (LayoutCoordinates) -> Unit,
) {
    val cardColor = if (isSystemInDarkTheme()) {
        colorResource(id = R.color.auto_complete_light)
    } else {
        colorResource(id = R.color.auto_complete_night)
    }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.padding(start = 3.dp),
            text = text,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(20.dp),
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSizeUpdate(coordinates)
                        },
                    value = selectedEmployer,
                    onValueChange = {
                        // expandedUpdate(!expanded)
                        selectedEmployerUpdate(it)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        // disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expandedUpdate(!expanded) }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black,
                            )
                        }
                    },
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    elevation = CardDefaults.cardElevation(15.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(cardColor),
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 100.dp)
                            .background(Color.Transparent),

                    ) {
                        if (selectedEmployer.isNotEmpty()) {
                            items(
                                employers.filter {
                                    it.lowercase().contains(selectedEmployer.lowercase())
                                }.sorted(),
                            ) {
                                CategoryItems(
                                    title = it,
                                    expandedUpdate = expandedUpdate,
                                    selectedEmployerUpdate = selectedEmployerUpdate,
                                )
                            }
                        } else {
                            items(
                                employers.sorted(),
                            ) {
                                CategoryItems(
                                    title = it,
                                    expandedUpdate = expandedUpdate,
                                    selectedEmployerUpdate = selectedEmployerUpdate,
                                )
//                                ) { title ->
//                                    expandedUpdate(false)
//                                    selectedEmployerUpdate(title)
//                                }
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
    expandedUpdate: (Boolean) -> Unit,
    selectedEmployerUpdate: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //expandedUpdate(false)
                selectedEmployerUpdate(title)
            }
            .padding(10.dp)
            .background(Color.Transparent),
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
        )
    }
}
