package com.example.triptrack.screen.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triptrack.domain.usecaces.local_data.bank_data.BankDataUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserState(
    val passport: String = "",
    val snils: String = "",
    val bankData: String = "",
    val inn: String = "",
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val bankDataUseCases: BankDataUseCases,
) : ViewModel() {

    var imageUri by mutableStateOf<Uri?>(null)
    var bitmap by mutableStateOf<Bitmap?>(null)

    private val _uiState = MutableStateFlow(UserState())
    var uiState: StateFlow<UserState> = _uiState.asStateFlow()

    init {
    }

    fun setBitmap(context: Context) {
        viewModelScope.launch {
            imageUri?.let {
                bitmap = if(Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    }
}
