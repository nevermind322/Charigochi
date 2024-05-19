package com.example.charigochi.domain

import com.example.charigochi.data.ProgressRepository
import com.example.charigochi.model.Progress
import com.example.charigochi.utils.isYesterdayFor
import com.example.charigochi.utils.twoDatesIsSameDayOfYear
import kotlinx.coroutines.flow.first
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadProgressUsecase @Inject constructor(private val repo: ProgressRepository) {


    suspend operator fun invoke(): Progress {
        val money = repo.moneyFlow.first()
        val streak = getStreak()
        val lastRewardClaim = repo.getLastRewardClaim()
        val isRewardClaimedToday = twoDatesIsSameDayOfYear(Date(lastRewardClaim), Date())
        return Progress(money = money, streak = streak, isRewardClaimedToday = isRewardClaimedToday)
    }

    private suspend fun getStreak(): Int {
        val lastLogin = repo.getLastLogin()
        val today = Date()
        repo.updateLastLogin(today)
        val prevLoginDate = Date(lastLogin)
        return if (prevLoginDate isYesterdayFor today) {
            val streak = repo.getStreak()
            repo.updateStreak(streak + 1)
            streak
        } else if (twoDatesIsSameDayOfYear(today, prevLoginDate)) {
            repo.getStreak()
        } else {
            repo.resetStreak()
            1
        }
    }
}