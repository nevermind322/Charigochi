package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.domain.BuyCatUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseCatViewModel @Inject constructor(private val buyCatUsecase: BuyCatUsecase) :
    ViewModel() {
    fun buyCat(catId: Int) {
        viewModelScope.launch {
            buyCatUsecase.buyCat(catId)
        }
    }

}

