package com.example.triptrack.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.triptrack.R
import com.example.triptrack.ui.theme.fontBold
import com.example.triptrack.ui.theme.fontItalic
import com.example.triptrack.ui.theme.fontRegular

@Composable
fun UserScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(top = 50.dp)
                .size(200.dp)
                .clip(CircleShape),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = CircleShape),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color.LightGray),
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(CircleShape)
                    .align(alignment = Alignment.BottomEnd)
                    .padding(bottom = 25.dp, end = 20.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Cyan),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            }
        }
        Text(
            text = "вставь сбда стэйт",
            modifier = Modifier.padding(top = 10.dp),
            fontFamily = fontRegular,
            fontSize = 28.sp,
        )
        ProfileCard()
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 130.dp, start = 50.dp, end = 50.dp),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.8f),
        )
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable {},
        ) {
            Text(
                text = "Написать разработчику",
                color = Color.Blue.copy(alpha = 0.7f),
                fontFamily = fontItalic,
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun ProfileCard() {
    ElevatedCard(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .padding(top = 30.dp)
            .height(80.dp)
            .clickable {

            },
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = R.color.card_elev),
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileRowCard(text = "Профиль", leadingIcon = Icons.Default.Person)
        }
    }
    ElevatedCard(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .padding(top = 30.dp)
            .height(80.dp)
            .clickable {},
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = R.color.card_elev),
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileRowCard(text = "Заказчики", leadingIcon = Icons.AutoMirrored.Filled.List)
        }
    }
    ElevatedCard(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .padding(top = 30.dp)
            .height(80.dp)
            .clickable {},
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = R.color.card_elev),
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileRowCard(text = "Поездки", leadingIcon = Icons.Default.Check)
        }
    }
}

@Composable
fun ProfileRowCard(
    text: String,
    leadingIcon: ImageVector,
) {
    Icon(
        imageVector = leadingIcon,
        contentDescription = null,
        modifier = Modifier.padding(start = 25.dp),
    )
    Text(text = text, fontFamily = fontBold, fontSize = 22.sp)
    Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = null,
        modifier = Modifier.padding(end = 25.dp),
    )
}

// fun openTelegram( ) {
//    val url = "https://api.whatsapp.com/send?phone=$toNumber"
//    try {
//        context.packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
//        context.startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
//    } catch (e: PackageManager.NameNotFoundException) {
//        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))}}
// }

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    val navController = rememberNavController()
    UserScreen(
        navController = navController,
    )
}
