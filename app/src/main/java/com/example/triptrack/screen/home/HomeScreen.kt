package com.example.triptrack.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.triptrack.ui.theme.fontBold

@Composable
fun HomeScreen(
    state: State<HomeState>,
    navController: NavController,
    onClick: () -> Unit,
) {
    var XX by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextButton(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(top = 500.dp).size(width = 250.dp, height = 150.dp),
            onClick = { onClick() },
            colors = ButtonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black,
                disabledContainerColor = Color.Magenta,
                disabledContentColor = Color.Black,
            ),
            content = {
                Text(text = "Добавить новую поездку", fontFamily = fontBold, fontSize = 18.sp)
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    // val navController = rememberNavController()
    // HomeScreen(state = homestate, navController = navController)
}
