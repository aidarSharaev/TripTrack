package com.example.triptrack.screen.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.triptrack.R
import com.example.triptrack.presentation.navgraph.Route
import com.example.triptrack.ui.theme.fontBold
import com.example.triptrack.ui.theme.fontItalic
import com.example.triptrack.ui.theme.fontRegular

@Composable
fun UserScreen(
    navController: NavController,
    viewModel: UserViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            viewModel.imageUri = uri
        }
    viewModel.setBitmap(context = context)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(top = 50.dp)
                .size(200.dp),
        ) {
            viewModel.bitmap?.let {
                Image(

                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            } ?: run {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = CircleShape),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.LightGray),
                )
            }
            IconButton(
                onClick = { launcher.launch("image/*") },
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
        ProfileCard(navController = navController)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Text(
                text = "Написать разработчику",
                Modifier.clickable { }.padding(bottom = 15.dp),
                color = Color.Blue.copy(alpha = 0.7f),
                fontFamily = fontItalic,
                fontSize = 18.sp,

            )
        }
    }
}

@Composable
fun ProfileCard(
    navController: NavController
) {
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
                       navController.navigate(route = Route.ProfileScreen.route)
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

// @Preview(showBackground = true)
// @Composable
// fun UserScreenPreview() {
//    val navController = rememberNavController()
//    UserScreen(
//        navController = navController,
//    )
// }
