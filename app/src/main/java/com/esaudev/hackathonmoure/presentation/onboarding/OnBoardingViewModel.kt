package com.esaudev.hackathonmoure.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.hackathonmoure.domain.usecase.SaveOnBoardingStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnBoardingStatusUseCase: SaveOnBoardingStatusUseCase
): ViewModel() {

    fun saveOnBoardingState() {
        viewModelScope.launch(Dispatchers.IO) {
            saveOnBoardingStatusUseCase()
        }
    }

}