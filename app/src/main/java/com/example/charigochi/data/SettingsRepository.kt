package com.example.charigochi.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.charigochi.data.db.SettingsDataStore
import com.example.charigochi.model.DEFAULT_THEME_KEY
import com.example.charigochi.model.Track
import com.example.charigochi.model.Theme
import com.example.charigochi.utils.currentThemeKey
import com.example.charigochi.utils.currentTrack
import com.example.charigochi.utils.isSoundOnKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepository @Inject constructor(@SettingsDataStore private val ds: DataStore<Preferences>) {


    val themeFlow = ds.data.map { it[currentThemeKey] ?: DEFAULT_THEME_KEY }
    val isSoundOnFlow = ds.data.map { it[isSoundOnKey] ?: true }
    val trackFLow = ds.data.map { it[currentTrack] ?: "calm" }

    suspend fun getTheme() =
        ds.data.map {
            com.example.charigochi.model.getTheme(
                it[currentThemeKey] ?: DEFAULT_THEME_KEY
            )
        }.first()

    suspend fun setTheme(theme: Theme) {
        ds.edit {
            it[currentThemeKey] = theme.key
        }
    }

    suspend fun getSoundOn() =
        ds.data.map { it[isSoundOnKey] ?: true }.first()

    suspend fun setSoundOn(isSoundOn: Boolean) {
        ds.edit {
            it[isSoundOnKey] = isSoundOn
        }
    }

    suspend fun getTrack() =
        ds.data.map { it[currentTrack] ?: "calm" }.first()

    suspend fun setTrack(track: Track) {
        ds.edit {
            it[currentTrack] = track.name.lowercase()
        }
    }
}


