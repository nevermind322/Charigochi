package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.CatRepo
import com.example.charigochi.data.db.CatEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val catRepo: CatRepo) : ViewModel() {

    val state = MutableStateFlow<AppUiState>(AppUiState.Loading)

    fun getCats() {
        viewModelScope.launch {
            val res = try {
                val res = catRepo.getAll()
                if (res.isEmpty()) {
                    updateCats()
                    val res2 = catRepo.getAll()
                    AppUiState.Success(res2)
                } else
                    AppUiState.Success(res)
            } catch (e: Exception) {
                AppUiState.Error(e)
            }
            state.value = res
        }
    }

    fun updateCats() {
        viewModelScope.launch {
            val cats = catRepo.getAll()
            val date = Date()
            if (cats.isEmpty())
                catRepo.insertCat(CatEntity(0, 100, date, 100, date))
            else {
                for (cat in cats) {
                    val ms = date.time - cat.lastBellyfulUpdate.time
                    val hours = TimeUnit.HOURS.convert(ms, TimeUnit.MILLISECONDS).toInt()
                    val bellyful = (cat.bellyful - 3 * hours).let { if (it >= 0) it else 0 }
                    catRepo.updateCat(cat.copy(bellyful = bellyful, lastBellyfulUpdate = date))
                }
            }
        }

    }
}

sealed class AppUiState {
    data object Loading : AppUiState()
    data class Success(val cats: List<CatEntity>) : AppUiState()
    data class Error(val e: Throwable) : AppUiState()
}

