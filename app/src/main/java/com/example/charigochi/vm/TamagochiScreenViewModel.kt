package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.CatRepo
import com.example.charigochi.data.db.CatEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TamagochiScreenViewModel @Inject constructor(val catRepo: CatRepo) : ViewModel() {
    fun feed(cat: CatEntity) {
        viewModelScope.launch {
            val newCat = cat.copy(
                bellyful = cat.bellyful + 3,
                happiness = cat.happiness + 3,
                lastBellyfulUpdate = Date()
            )
            catRepo.updateCat(newCat)

        }
    }

    fun care(cat: CatEntity) {
        viewModelScope.launch {
            val newCat = cat.copy(happiness = cat.happiness + 3, happinessLastUpdate = Date())
            catRepo.updateCat(newCat)
        }
    }
}