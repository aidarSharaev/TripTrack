package com.example.triptrack.screen.profile

import android.content.Intent
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.R
import com.example.triptrack.presentation.component.bottomBorder
import com.example.triptrack.ui.theme.fontRegular

@Composable
fun ProfileScreen() {
    var a by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

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
                value = "160-798-243 92",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                label = { Text(text = "СНИЛС", fontSize = 12.sp) },
                textStyle = TextStyle(fontSize = 15.sp, fontFamily = fontRegular),
            )

            TextField(
                value = a,
                onValueChange = { itt ->
                    a = itt
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .height(120.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray,
                    focusedContainerColor = colorResource(id = R.color.aqua),
                ),
                label = { Text(text = "Реквизиты", fontSize = 12.sp) },
                textStyle = TextStyle(fontSize = 15.sp, fontFamily = fontRegular),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                placeholder = { Text(text = "Введите реквизиты") },
            )

            TextButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    Intent(Intent.ACTION_SEND).also { intent ->
                        intent.putExtra(Intent.EXTRA_TEXT, "hi")
                        intent.type = "text/plain"
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    }
                },
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

// private fun openGNIVTSSelfEmployed(context: Context) {
//    var launchIntent: Intent? = null
//    try {
//        launchIntent = context.packageManager.getLaunchIntentForPackage("com.huawei.calendar")
//    } catch (ignored: Exception) {
//    }
//    if (launchIntent != null) {
//        launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        context.startActivity(launchIntent)
//    } else {
//        launchIntent = context.packageManager.getLaunchIntentForPackage("com.huawei.calendar")
//        context.startActivity(launchIntent)
//        Log.d("AAAA", "else")
//    }
// }

// "com.gnivts.selfemployed"

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
