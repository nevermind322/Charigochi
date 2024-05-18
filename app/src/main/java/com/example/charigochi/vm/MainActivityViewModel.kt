package com.example.charigochi.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.SettingsRepository
import com.example.charigochi.data.Theme
import com.example.charigochi.data.getTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    private val settingsStateFlow =
        settingsRepository.isSoundOnFlow.combine(settingsRepository.themeFlow) { isSoundOn, theme ->
            SettingsState.Success(
                isSoundOn,
                getTheme(theme)
            )
        }
    val stateFlow = settingsStateFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        SettingsState.Loading
    )

}


sealed class SettingsState() {
    data object Loading : SettingsState()
    data class Success(val isSoundOn: Boolean, val theme: Theme) : SettingsState()


}

