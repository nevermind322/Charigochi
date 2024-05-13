package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val progressRepo: ProgressRepository
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
                AppUiState.Success(cats, money, streak, todayRewardClaimed)
            } catch (e: Exception) {
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
    ) : AppUiState()

    data class Error(val e: Throwable) : AppUiState()
}

