package com.example.triptrack.di

import com.example.triptrack.domain.manager.usecaces.ReadAppEntry
import com.example.triptrack.domain.manager.usecaces.SaveAppEntry

data class AppEntryUseCases(
  val readAppEntry: ReadAppEntry,
  val saveAppEntry: SaveAppEntry
)
