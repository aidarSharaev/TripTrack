package com.example.triptrack.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triptrack.domain.usecaces.local_data.bank_data.BankDataUseCases
import com.example.triptrack.domain.usecaces.local_data.profile_info.ProfileUseCases
import com.example.triptrack.model.BankData
import com.example.triptrack.model.ProfileInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val bankDataUseCases: BankDataUseCases,
    private val profileUseCases: ProfileUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    var uiState: StateFlow<ProfileState> = _uiState.asStateFlow()

    init {
        getData()
    }

    fun saveNewData() {
        viewModelScope.launch {
            if (checkValid()) {
                Log.d("AAAA", "if")
                _uiState.value.apply {
                    bankDataUseCases.insertBankData(
                        bankData = BankData(
                            id = 0,
                            requisites = requisites,
                            snils = snils,
                            inn = inn,
                            passport = passport,
                        ),
                    )
                    profileUseCases.insertProfile(
                        ProfileInfo(
                            id = 0,
                            firstName = firstName,
                            lastName = lastName,
                        ),
                    )
                }
            } else {
                Log.d("AAAA", "else")
            }
        }
    }

    private fun getData() {
        profileUseCases.getProfile().onEach {
            it?.let { name ->
                _uiState.update { newState ->
                    newState.copy(
                        firstName = name.firstName,
                        lastName = name.lastName,
                    )
                }
            } ?: run {
                _uiState.update { newState ->
                    newState.copy(
                        firstName = "Имя",
                        lastName = "Фамилия",
                    )
                }
            }
        }.launchIn(viewModelScope)

        bankDataUseCases.getBankData().onEach {
            it?.let {
                _uiState.update { newState ->
                    newState.copy(
                        passport = it.passport,
                        snils = it.snils,
                        requisites = it.requisites,
                        inn = it.inn,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setLastNameValue(value: String) {
        if (value.length <= 15) {
            _uiState.update {
                it.copy(lastName = value)
            }
        }
    }

    fun setFirstNameValue(value: String) {
        if (value.length <= 15) {
            _uiState.update {
                it.copy(firstName = value)
            }
        }
    }

    fun setRequisitesValue(value: String) {
        deleteNewStringChar(value = value)
        _uiState.update {
            it.copy(requisites = value)
        }
    }

    private fun deleteNewStringChar(value: String) {
        value.apply {
            if (length > 2 && last() == '\n') {
                substring(0, value.length - 1)
            }
        }
    }

    fun setInnValue(value: String) {
        if (value.length <= 12) {
            _uiState.update {
                it.copy(inn = value)
            }
        }
    }

    fun setSnilsValue(value: String) {
        if (value.length <= 11) {
            _uiState.update {
                it.copy(snils = value)
            }
        }
    }

    fun setPassportValue(value: String) {
        if (value.length <= 10) {
            _uiState.update {
                it.copy(passport = value)
            }
        }
    }

    private fun checkValid(): Boolean {
        _uiState.value.apply {
            return lastName.length in 1..14
//            return lastName.length in 1..14 && firstName.length in 1..14 && snils.length == 11 && inn.length == 12 && passport.length == 10
        }
    }
}
