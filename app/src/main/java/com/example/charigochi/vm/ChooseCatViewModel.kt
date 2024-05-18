package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.CatRepo
import com.example.charigochi.data.ProgressRepository
import com.example.charigochi.domain.BuyCatUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseCatViewModel @Inject constructor(
    private val buyCatUsecase: BuyCatUsecase,
    catRepo: CatRepo,
    progressRepository: ProgressRepository
) : ViewModel() {

    val allCatsFlow = catRepo.allFlow
    val moneyFlow = progressRepository.moneyFlow

    fun buyCat(catId: Int) {
        viewModelScope.launch {
            buyCatUsecase.buyCat(catId)
        }
    }

}

