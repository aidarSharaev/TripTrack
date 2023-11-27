package com.example.triptrack.screen.profile

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triptrack.domain.usecaces.local_data.profile_info.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(
    private val profileUseCases: ProfileUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserState())
    var uiState: StateFlow<UserState> = _uiState.asStateFlow()

    var bitmap by mutableStateOf<Bitmap?>(null)

    init {
        getData()
    }

    private fun getData() {
        profileUseCases.getProfile().onEach {
            it?.let { info ->
                _uiState.update { state ->
                    state.copy(
                        firstName = info.firstName,
                        lastName = info.lastName,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

//    fun pickBitmap(context: Context) {
//        viewModelScope.launch {
//            _uiState.value.imageUri?.let {
//                _uiState.update { state ->
//                    state.copy(
//                        bitmap = if (Build.VERSION.SDK_INT < 28) {
//                            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
//                        } else {
//                            val source = ImageDecoder.createSource(context.contentResolver, it)
//                            ImageDecoder.decodeBitmap(source)
//                        },
//                    )
//                }
//                saveBitmap(context = context)
//            }
//        }
//    }
}
