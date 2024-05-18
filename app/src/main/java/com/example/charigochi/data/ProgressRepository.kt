package com.example.charigochi.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.charigochi.data.db.ProgressDataStore
import com.example.charigochi.utils.currentStreakKey
import com.example.charigochi.utils.lastLoginDateKey
import com.example.charigochi.utils.lastRewardClaimKey
import com.example.charigochi.utils.moneyKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgressRepository @Inject constructor(@ProgressDataStore private val dataStore: DataStore<Preferences>) {

    val moneyFlow = dataStore.data.map { it[moneyKey] ?: 500 }


    suspend fun addMoney(money: Int) {
        dataStore.edit {
            val cur = it[moneyKey] ?: 500
            it[moneyKey] = cur + money
        }
    }

    suspend fun spendMoney(money: Int) {
        dataStore.edit {
            val cur = it[moneyKey] ?: 500
            it[moneyKey] = cur - money
        }
    }

    suspend fun getStreak() =
        dataStore.data.map { it[currentStreakKey] ?: -1 }.first()

    suspend fun updateStreak(newStreak: Int) {
        dataStore.edit {
            it[currentStreakKey] = newStreak
        }
    }

    suspend fun resetStreak() {
        dataStore.edit {
            it[currentStreakKey] = 1
        }
    }

    suspend fun getLastLogin() = dataStore.data.map { it[lastLoginDateKey] ?: Date().time }.first()

    suspend fun updateLastLogin(date: Date) {
        dataStore.edit { it[lastLoginDateKey] = date.time }
    }


    suspend fun getLastRewardClaim() =
        dataStore.data.map { it[lastRewardClaimKey] ?: Date().time }.first()

    suspend fun updateLastRewardClaim(date: Date) {
        dataStore.edit {
            it[lastRewardClaimKey] = date.time
        }
    }
}