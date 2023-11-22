package com.example.triptrack.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.triptrack.domain.manager.LocalUserManager
import com.example.triptrack.utils.Constants
import com.example.triptrack.utils.Constants.DATA_STORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context,
) : LocalUserManager {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[PreferencesKey.APP_ENTRY] ?: false
            }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE)

object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
    val CURRENT_MONTH = intPreferencesKey(name = Constants.CURRENT_MONTH)
}
