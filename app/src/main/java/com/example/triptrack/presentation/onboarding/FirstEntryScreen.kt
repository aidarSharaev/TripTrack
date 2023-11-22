package com.example.triptrack.presentation.onboarding

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrack.R
import com.example.triptrack.presentation.component.CustomTextField

@Composable
fun FirstEntryScreen(
    firstName: String,
    firstNameChange: (String) -> Unit,
    secondName: String,
    secondNameChange: (String) -> Unit,
    onClick: () -> Unit,
    firstNameError: Boolean,
    secondNameError: Boolean,
) {
    val focusManager = LocalFocusManager.current

    val painter =
        if (isSystemInDarkTheme()) {
            painterResource(id = R.drawable.gradient_black)
        } else {
            painterResource(id = R.drawable.gradient_blue)
        }

    val textColor =
        if (isSystemInDarkTheme()) {
            colorResource(id = R.color.text_color)
        } else {
            colorResource(id = R.color.text_color_night)
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painter,
                contentScale = ContentScale.Crop,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomTextField(
            field = firstName,
            fieldChange = { firstNameChange(it) },
            textColor = textColor,
            text = stringResource(R.string.first_name),
            focusManager = focusManager,
            imeActions = ImeAction.Next,
            onClickAction = true,
            keyboardType = KeyboardType.Text,
            isError = firstNameError,
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            field = secondName,
            fieldChange = { secondNameChange(it) },
            textColor = textColor,
            text = stringResource(R.string.second_name),
            focusManager = focusManager,
            keyboardType = KeyboardType.Text,
            isError = secondNameError,
        )

        TextButton(
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 50.dp)
                .size(width = 96.dp, height = 48.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 0.dp,
            ),
            colors = ButtonDefaults.textButtonColors(
                containerColor = colorResource(id = R.color.button_color),
            ),
        ) {
            Text(text = stringResource(R.string.launch), color = textColor)
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
fun FirstEntryScreenPreview() {
    // FirstEntryScreen()
}

// todo length

// todo тряска
