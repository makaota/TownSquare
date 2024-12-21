package com.makaota.townsquare.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makaota.townsquare.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow<Boolean?>(null)
    val onBoardingState: StateFlow<Boolean?> = _onBoardingState

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect { isOnBoardingComplete ->
                _onBoardingState.value = isOnBoardingComplete
            }

        }
    }
}