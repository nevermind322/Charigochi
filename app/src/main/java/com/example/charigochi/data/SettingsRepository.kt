package com.example.charigochi.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.charigochi.data.db.SettingsDataStore
import com.example.charigochi.utils.currentThemeKey
import com.example.charigochi.utils.isSoundOnKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepository @Inject constructor(@SettingsDataStore private val ds: DataStore<Preferences>) {


    val themeFlow = ds.data.map { it[currentThemeKey] ?: DEFAULT_THEME_KEY }
    val isSoundOnFlow = ds.data.map { it[isSoundOnKey] ?: true }

    suspend fun getTheme() =
        ds.data.map { getTheme(it[currentThemeKey] ?: DEFAULT_THEME_KEY) }.first()

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
}


private const val DARK_THEME_KEY = "dark"
private const val LIGHT_THEME_KEY = "light"
private const val DEFAULT_THEME_KEY = "default"

enum class Theme(val key: String) {
    Dark(DARK_THEME_KEY), Light(LIGHT_THEME_KEY), Default(DEFAULT_THEME_KEY)
}

fun getTheme(key: String) = when (key) {
    LIGHT_THEME_KEY -> Theme.Light
    DARK_THEME_KEY -> Theme.Dark
    else -> Theme.Default
}