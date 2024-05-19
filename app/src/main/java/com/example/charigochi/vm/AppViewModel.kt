package com.example.charigochi.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.CatFactRepository
import com.example.charigochi.data.CatRepo
import com.example.charigochi.data.ProgressRepository
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.domain.LoadProgressUsecase
import com.example.charigochi.domain.UpdateCatsUsecase
import com.example.charigochi.model.LocaleProvider
import com.example.charigochi.model.Progress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    catRepo: CatRepo,
    progressRepo: ProgressRepository,
    private val updateCatsUsecase: UpdateCatsUsecase,
    private val loadProgressUsecase: LoadProgressUsecase,
    private val catFactRepository: CatFactRepository,
    private val localeProvider: LocaleProvider
) : ViewModel() {

    val state = MutableStateFlow<AppUiState>(AppUiState.Loading)

    val catsFlow = catRepo.allFlow
    val moneyFlow = progressRepo.moneyFlow
    val isRewardClaimedFlow = progressRepo.isRewardClaimedFlow

    fun init() {
        viewModelScope.launch {
            state.value = try {
                val cats = updateCatsUsecase()
                val progress = loadProgressUsecase()
                val locale = localeProvider.getCatFactLanguage()
                val fact = try {
                    catFactRepository.getFact(locale)
                } catch (e: Exception) {
                    locale.default
                }
                AppUiState.Success(cats, progress, fact)
            } catch (e: Exception) {
                Log.d("appUIState", e.message ?: "null")
                AppUiState.Error(e)
            }
        }
    }


}

sealed class AppUiState {
    data object Loading : AppUiState()
    data class Success(
        val cats: List<CatEntity>,
        val progress: Progress,
        val catFact: String
    ) : AppUiState()

    data class Error(val e: Throwable) : AppUiState()
}

