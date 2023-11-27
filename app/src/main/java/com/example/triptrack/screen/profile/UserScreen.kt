package com.example.triptrack.screen.profile

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.triptrack.R
import com.example.triptrack.presentation.navgraph.Route
import com.example.triptrack.ui.theme.fontBold
import com.example.triptrack.ui.theme.fontItalic
import com.example.triptrack.ui.theme.fontRegular
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun UserScreen(
    navController: NavController,
    viewModel: UserViewModel,
) {
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    LaunchedEffect(key1 = imageUri) {
        Log.d("AAAA", "Effect")
        bitmap = loadBitMap(imageUri = imageUri, context = context)
        saveBitmap(context = context, bitmap = bitmap)
    }

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
            bitmap?.asImageBitmap()?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
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
            text = "${uiState.value.firstName} ${uiState.value.lastName}",
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
                text = stringResource(R.string.write_to_developer),
                Modifier
                    .clickable {
                    }
                    .padding(bottom = 15.dp),
                color = Color.Blue.copy(alpha = 0.7f),
                fontFamily = fontItalic,
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun ProfileCard(
    navController: NavController,
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
            .clickable {
                navController.navigate(route = Route.OrderListScreen.route)
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
            ProfileRowCard(text = "Поездки", leadingIcon = Icons.AutoMirrored.Filled.List)
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
            .clickable {
                navController.navigate(route = Route.EmployerListScreen.route)
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
            ProfileRowCard(text = "Заказчики", leadingIcon = Icons.Default.Check)
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

fun loadBitMap(
    imageUri: Uri? = null,
    context: Context,
): Bitmap {
    val image = File(Environment.DIRECTORY_PICTURES + File.separator + "TripTrackAvatar3" + "userAvatar.jpg")
    if (image.exists()) {
        return BitmapFactory.decodeFile(image.absolutePath)
    }
    lateinit var returnBitmap: Bitmap
    imageUri?.let {
        returnBitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            ImageDecoder.decodeBitmap(source)
        }
    } ?: run {
        returnBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.circle_profile)
    }
    return returnBitmap
}

@RequiresApi(Build.VERSION_CODES.R)
fun saveBitmap(
    context: Context,
    bitmap: Bitmap?,
) {
    val filename = "userAvatar.jpg"
    var fos: OutputStream? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        context.contentResolver?.also { resolver ->
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "TripTrackAvatar3",
                )
            }
            resolver.delete(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
            )
            val uri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = uri?.let {
                resolver.openOutputStream(it)
            }
        }
    } else {
        val imagesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + File.separator + "TripTrackAvatar3")
        val image = File(imagesDir, filename)
        fos = FileOutputStream(image)
    }
    fos?.use {
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
    }
}
