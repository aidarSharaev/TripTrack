package com.example.triptrack.screen.new_order_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.triptrack.R
import com.example.triptrack.presentation.component.AutoComplete
import com.example.triptrack.presentation.component.CheckboxTooltip
import com.example.triptrack.presentation.component.DateSelection
import com.example.triptrack.utils.Constants.MONEY_VALUES_DOWN
import com.example.triptrack.utils.Constants.MONEY_VALUES_UP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOrderScreen(
    viewModel: NewOrderViewModel,
    navigateUp: () -> Unit,
    saveNewOrder: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    val checkedState = remember { mutableStateOf(true) }

//    val painter = if (isSystemInDarkTheme()) {
//        painterResource(id = R.drawable.gradient_black)
//    } else {
//        painterResource(id = R.drawable.gradient_blue)
//    }
//
//    val textColor = if (isSystemInDarkTheme()) {
//        colorResource(id = R.color.text_color)
//    } else {
//        colorResource(id = R.color.text_color_night)
//    }

    val routeValue = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val secondNameValue = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(text = stringResource(id = R.string.new_order))
                    }
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = { navigateUp() },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .paint(
                    painter = painterResource(id = R.drawable.gradient_blue), // TODO painter
                    contentScale = ContentScale.Crop,
                ),
            verticalArrangement = Arrangement.Top,
        ) {
            DateSelection(context = context)

            Spacer(modifier = Modifier.height(20.dp))

            NewOrderCard(
                selectedEmployer = uiState.value.selectedEmployer,
                employers = uiState.value.employerList,
                checked = checkedState,
                profit = viewModel.profitValue,
                cost = viewModel.costValue,
                profitUpdate = {
                    viewModel.profitUpdate(profitValue = it)
                },
                costUpdate = {
                    viewModel.costUpdate(costValue = it)
                },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 35.dp, end = 35.dp)
                    .height(40.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "${stringResource(id = R.string.total)}: ${uiState.value.total}",
                )
            }
            TextButton(
                onClick = { saveNewOrder },

                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(
                        width = 120.dp,
                        height = 48.dp,
                    )
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 0.dp,
                ),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = colorResource(id = R.color.button_color),
                ),
            ) {
                Text(
                    text = stringResource(R.string.complete),
                    color = Color.Black,
                ) // TODO textcolor
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOrderCard(
    selectedEmployer: String,
    employers: MutableSet<String>,
    checked: MutableState<Boolean>,
    profit: String,
    cost: String,
    profitUpdate: (String) -> Unit,
    costUpdate: (String) -> Unit,
) {
    val tooltipState = rememberTooltipState(isPersistent = true)
    Box(
        modifier = Modifier
            .height(470.dp)
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(color = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 130.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CheckboxTooltip(
                tooltipState = tooltipState,
                checked = checked,
                text = stringResource(id = R.string.tax),
            )
            CheckboxTooltip(
                tooltipState = tooltipState,
                checked = checked,
                text = stringResource(id = R.string.payment),
            )
        }
        AutoComplete(
            selectedEmployer = selectedEmployer,
            employers = employers,
            text = stringResource(id = R.string.employer),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 210.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                shadowElevation = 5.dp,
            ) {
                OutlinedTextField(
                    value = profit,
                    onValueChange = profitUpdate,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.nil),
                            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                        )
                    },
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                repeat(3) {
                    Surface(shadowElevation = 5.dp) {
                        ButtonWithMoneyValue(
                            field = profit,
                            text = MONEY_VALUES_UP[it],
                            onClick = profitUpdate,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = cost,
                onValueChange = costUpdate,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.nil),
                        fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                    )
                },
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                repeat(3) {
                    Surface(shadowElevation = 5.dp) {
                        ButtonWithMoneyValue(
                            field = cost,
                            text = MONEY_VALUES_DOWN[it],
                            onClick = costUpdate,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewOrderScreenPreview() {
    val newOrderViewModel: NewOrderViewModel = hiltViewModel()
    NewOrderScreen(newOrderViewModel, { }, {})
}

@Composable
fun ButtonWithMoneyValue(
    field: String,
    text: String,
    onClick: (String) -> Unit,
) {
    Button(
        shape = RoundedCornerShape(5.dp),
        onClick = {
            val arg = (field.toInt() + (text.substring(1)).toInt()).toString()
            onClick(arg)
        },
        modifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(
                    id = R.color.tool_tip,
                ),
            )
            .height(35.dp)
            .animateContentSize(),
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Black,
        ),
    ) {
        Text(text = text)
    }
}

// todo onClick button
