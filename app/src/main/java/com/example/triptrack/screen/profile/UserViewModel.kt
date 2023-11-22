package com.example.triptrack.screen.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class UserState(
    val routes: MutableSet<String> = mutableSetOf<String>()
)

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel()
