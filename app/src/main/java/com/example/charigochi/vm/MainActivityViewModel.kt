package com.example.charigochi.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.model.MusicProvider
import com.example.charigochi.data.SettingsRepository
import com.example.charigochi.model.Track
import com.example.charigochi.model.Settings
import com.example.charigochi.model.getTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val musicProvider: MusicProvider
) :
    ViewModel() {

    private val settingsStateFlow =
        settingsRepository.isSoundOnFlow.combine(settingsRepository.themeFlow) { isSoundOn, theme -> isSoundOn to theme }
            .combine(settingsRepository.trackFLow) { pair, track ->
                SettingsState.Success(
                    Settings(
                        pair.first,
                        getTheme(pair.second),
                        Track.valueOf(track.uppercase())
                    )
                )
            }

    val stateFlow = settingsStateFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        SettingsState.Loading
    )

    fun getMusicUri(track: Track) = when (track) {
        Track.CALM -> musicProvider.calm
        Track.ANXIOUS -> musicProvider.anxious
        Track.TIRED -> musicProvider.tired
        Track.FUNKY -> musicProvider.funky
    }
}


sealed class SettingsState() {
    data object Loading : SettingsState()
    data class Success(val settings: Settings) : SettingsState()


}

