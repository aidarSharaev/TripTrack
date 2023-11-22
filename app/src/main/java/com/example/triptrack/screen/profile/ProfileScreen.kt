package com.example.triptrack.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.presentation.component.bottomBorder
import com.example.triptrack.ui.theme.fontRegular

@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopBar()
        },
    ) {
        Column(
            modifier = Modifier.padding(it).fillMaxSize().background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextField(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(60.dp)
                        .bottomBorder(0.dp, Color.Transparent),
                    value = "9216 157617",
                    onValueChange = {},
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    label = { Text(text = "Паспорт", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 15.sp, fontFamily = fontRegular),

                )
                TextField(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(60.dp)
                        .bottomBorder(0.dp, Color.Transparent),
                    value = "166014422974",
                    onValueChange = {},
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    label = { Text(text = "ИНН", fontSize = 12.sp) },
                    textStyle = TextStyle(fontSize = 15.sp, fontFamily = fontRegular),

                )
            }
            TextField(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                value = "СНИЛС",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                label = { Text(text = "160-798-243 92", fontSize = 12.sp) },
                textStyle = TextStyle(fontSize = 15.sp, fontFamily = fontRegular),
            )
            TextButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(
                    text = "Реквизиты счета",
                    fontSize = 17.sp,
                    fontFamily = fontRegular,
                    color = Color.Black,
                )
            }
            TextButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            ) {
                Text(
                    text = "Открыть Мой Налог",
                    fontSize = 17.sp,
                    fontFamily = fontRegular,
                    color = Color.Black,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "Профиль")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen()
}
