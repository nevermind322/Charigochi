package com.example.charigochi.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


val Context.progressDataStore by preferencesDataStore(name = "progress")
val moneyKey = intPreferencesKey("money")
val lastLoginDateKey = longPreferencesKey("lastLogin")
val currentStreakKey = intPreferencesKey("streak")
val lastRewardClaimKey = longPreferencesKey("lastRewardClaim")

val Context.settingsDataStore by preferencesDataStore(name = "settings")
val isSoundOnKey = booleanPreferencesKey("isSoundOn")
val currentThemeKey = stringPreferencesKey("theme")

val streakToMoney = mapOf(
    1 to 100,
    2 to 150,
    3 to 200,
    4 to 250,
    5 to 300,
    6 to 350,
    7 to 500
)