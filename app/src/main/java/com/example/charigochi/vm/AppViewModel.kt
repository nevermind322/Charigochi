package com.example.charigochi.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.CatFactLanguage
import com.example.charigochi.data.CatFactRepository
import com.example.charigochi.data.ProgressRepository
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.domain.UpdateAndGetCatsUsecase
import com.example.charigochi.utils.isYesterdayFor
import com.example.charigochi.utils.twoDatesIsSameDayOfYear
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val usecase: UpdateAndGetCatsUsecase,
    private val progressRepo: ProgressRepository,
    private val catFactRepository: CatFactRepository
) : ViewModel() {

    val state = MutableStateFlow<AppUiState>(AppUiState.Loading)
    fun init() {
        viewModelScope.launch {
            state.value = try {
                val cats = usecase()
                val money = progressRepo.moneyFlow.first()
                val streak = getStreak()
                val lastRewardClaim = progressRepo.getLastRewardClaim()
                val todayRewardClaimed = twoDatesIsSameDayOfYear(Date(lastRewardClaim), Date())
                val fact = try {
                    catFactRepository.getFact(CatFactLanguage.RUSSIAN)
                } catch (e: Exception) {
                    CatFactLanguage.RUSSIAN.default
                }
                AppUiState.Success(cats, money, streak, todayRewardClaimed, fact)
            } catch (e: Exception) {
                Log.d("appUIState", e.message ?: "null")
                AppUiState.Error(e)
            }
        }
    }

    private suspend fun getStreak(): Int {
        val lastLogin = progressRepo.getLastLogin()
        val today = Date()
        progressRepo.updateLastLogin(today)
        val prevLoginDate = Date(lastLogin)
        return if (prevLoginDate isYesterdayFor today) {
            val streak = progressRepo.getStreak()
            progressRepo.updateStreak(streak + 1)
            streak
        } else if (twoDatesIsSameDayOfYear(today, prevLoginDate)) {
            progressRepo.getStreak()
        } else {
            progressRepo.resetStreak()
            1
        }
    }
}

sealed class AppUiState {
    data object Loading : AppUiState()
    data class Success(
        val cats: List<CatEntity>,
        val money: Int,
        val streak: Int,
        val rewardClaimedToday: Boolean,
        val catFact: String
    ) : AppUiState()

    data class Error(val e: Throwable) : AppUiState()
}

