package com.example.triptrack.presentation.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triptrack.domain.usecaces.app_entry.AppEntryUseCases
import com.example.triptrack.domain.usecaces.local_data.profile_info.ProfileUseCases
import com.example.triptrack.model.ProfileInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val profileUseCases: ProfileUseCases,
) : ViewModel() {

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }

    private val _uiState = MutableStateFlow(OnBoardingState())
    var uiState = _uiState.asStateFlow()
    private val length: Int = 15
    var firstName by mutableStateOf(value = "")
        private set
    var secondName by mutableStateOf(value = "")
        private set
    var firstNameIsEmpty by mutableStateOf(value = false)
        private set
    var secondNameIsEmpty by mutableStateOf(value = false)
        private set
    private val pattern = Regex("[а-яА-Я\\s]*")

    fun onCompleteButtonClick() {
        if (valid()) {
            saveAppEntry()
            addNewUser()
        }
    }

    private fun addNewUser() {
        viewModelScope.launch {
            profileUseCases.insertProfile(
                ProfileInfo(
                    id = 0,
                    firstName = firstName,
                    lastName = secondName,
                ),
            )
        }
    }

    fun firstNameChange(string: String) {
        if (string.length < length && string.matches(pattern)) {
            firstName = string
        }
    }

    fun secondNameChange(string: String) {
        if (string.length < length && string.matches(pattern)) {
            secondName = string
        }
    }

    private fun valid(): Boolean {
        if (_uiState.value.firstName.isEmpty()) firstNameIsEmpty = true
        if (_uiState.value.secondName.isEmpty()) secondNameIsEmpty = true
        return firstName.isNotEmpty() && secondName.isNotEmpty()
    }
}
