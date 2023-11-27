package com.example.triptrack.screen.profile

import android.graphics.Bitmap
import android.net.Uri

data class UserState(
    var firstName: String = "",
    var lastName: String = "",
    var imageUri: Uri? = null,
    var bitmap: Bitmap? = null,
)
