package com.example.triptrack.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.triptrack.domain.manager.MonthsCounterManager
import com.example.triptrack.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MonthsCounterManagerImpl(
    private val context: Context,
) : MonthsCounterManager {
    override fun readCurrentMonth(): Flow<Int> {
        return context.dataStore.data
            .map { preferences ->
                preferences[PreferencesKey.CURRENT_MONTH] ?: 0
            }
    }

    override suspend fun saveCurrentMonth(month: Int) {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.CURRENT_MONTH] = month
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATA_STORE)
