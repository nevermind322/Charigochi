package com.example.charigochi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charigochi.data.SettingsRepository
import com.example.charigochi.model.Track
import com.example.charigochi.model.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    fun changeTheme(theme: Theme) {
        viewModelScope.launch {
            settingsRepository.setTheme(theme)
        }
    }

    fun changeSound(isSoundOn: Boolean) {
        viewModelScope.launch {
            settingsRepository.setSoundOn(isSoundOn)
        }
    }

    fun changeTrack(track: Track) {
        viewModelScope.launch {
            settingsRepository.setTrack(track)
        }
    }

}