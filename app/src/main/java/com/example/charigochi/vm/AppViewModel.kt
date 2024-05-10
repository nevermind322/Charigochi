package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.db.CatEntity
import com.example.charigochi.domain.UpdateAndGetCatsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val usecase: UpdateAndGetCatsUsecase) : ViewModel() {

    val state = MutableStateFlow<AppUiState>(AppUiState.Loading)
    private var initJob: Job? = null

    fun init() {
        initJob = viewModelScope.launch {
            state.value = try {
                val cats = usecase()
                AppUiState.Success(cats)
            } catch (e: Exception) {
                AppUiState.Error(e)
            }
        }
    }
}

sealed class AppUiState {
    data object Loading : AppUiState()
    data class Success(val cats: List<CatEntity>) : AppUiState()
    data class Error(val e: Throwable) : AppUiState()
}

