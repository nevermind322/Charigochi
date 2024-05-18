package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.ProgressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val progressRepository: ProgressRepository) :
    ViewModel() {

    val moneyFlow = progressRepository.moneyFlow
    val isRewardClaimedFlow = progressRepository.isRewardClaimedFlow
    fun claimReward(reward: Int) {
        viewModelScope.launch {
            progressRepository.addMoney(reward)
            progressRepository.updateLastRewardClaim(Date())
        }
    }

}