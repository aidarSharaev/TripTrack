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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triptrack.R
import com.example.triptrack.presentation.component.bottomBorder
import com.example.triptrack.ui.theme.fontRegular

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(navigateUp = navigateUp)
        },
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProfileTextField(
                    label = stringResource(R.string.first_name),
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp),
                    value = uiState.firstName,
                    onValueChange = { string -> viewModel.setFirstNameValue(string) },
                    keyboardActions = KeyboardActions {
                        focusManager.clearFocus()
                    },
                    keyboardType = KeyboardType.Text,
                )
                ProfileTextField(
                    label = stringResource(R.string.second_name),
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp),
                    value = uiState.lastName,
                    onValueChange = { string -> viewModel.setLastNameValue(string) },
                    keyboardActions = KeyboardActions {
                        focusManager.clearFocus()
                    },
                    keyboardType = KeyboardType.Text,
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProfileTextField(
                    label = stringResource(R.string.passport),
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp),
                    value = uiState.passport,
                    onValueChange = { string -> viewModel.setPassportValue(string) },
                    keyboardActions = KeyboardActions {
                        focusManager.clearFocus()
                    },
                )
                ProfileTextField(
                    label = stringResource(R.string.inn),
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp),
                    value = uiState.inn,
                    onValueChange = { string -> viewModel.setInnValue(string) },
                    keyboardActions = KeyboardActions {
                        focusManager.clearFocus()
                    },
                )
            }

            ProfileTextField(
                label = stringResource(R.string.snils),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .height(80.dp)
                    .fillMaxWidth(),
                value = uiState.snils,
                onValueChange = { string -> viewModel.setSnilsValue(string) },
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
            )

            ProfileTextField(
                label = stringResource(R.string.requisites),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                value = uiState.requisites,
                onValueChange = { string -> viewModel.setRequisitesValue(string) },
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                keyboardType = KeyboardType.Text,
            )

            ProfileButton(
                text = stringResource(id = R.string.send_data),
                color = Color.White,
                onClick = {
                    Intent(Intent.ACTION_SEND).also { intent ->
                        intent.putExtra(
                            Intent.EXTRA_TEXT,
                            "" +
                                "Имя: ${uiState.firstName}\n" +
                                "Фамилия: ${uiState.lastName}\n" +
                                "Паспорт: ${uiState.passport}\n" +
                                "ИНН: ${uiState.inn}\n" +
                                "СНИЛС: ${uiState.snils}\n" +
                                "Реквизиты:\n${uiState.inn}\n",
                        )
                        intent.type = "text/plain"
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    }
                },
            )

            ProfileButton(
                onClick = {
                    viewModel.saveNewData()
                },
            )
        }
    }
}

// "com.gnivts.selfemployed"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navigateUp: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = "Профиль")
        },
        navigationIcon = {
            IconButton(onClick = { navigateUp() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
    )
}

@Composable
private fun ProfileTextField(
    value: String,
    label: String,
    placeholder: String = stringResource(id = R.string.enter_data),
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    keyboardActions: KeyboardActions,
) {
    TextField(
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .bottomBorder(0.dp, Color.Transparent),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        label = { Text(text = label, fontSize = 12.sp) },
        placeholder = {
            Text(text = placeholder, fontSize = 12.sp, fontFamily = fontRegular)
        },
        textStyle = TextStyle(fontSize = 15.sp, fontFamily = fontRegular),
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done,
        ),
    )
}

@Composable
private fun ProfileButton(
    color: Color = Color.Green,
    text: String = stringResource(R.string.save_change),
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 30.dp)
            .height(60.dp)
            .fillMaxWidth(),

        colors = ButtonDefaults.buttonColors(containerColor = color),
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontFamily = fontRegular,
            color = Color.Black,
        )
    }
}
