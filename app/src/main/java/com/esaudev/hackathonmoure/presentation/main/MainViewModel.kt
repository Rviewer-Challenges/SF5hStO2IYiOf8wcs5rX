package com.esaudev.hackathonmoure.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esaudev.hackathonmoure.domain.model.Message
import com.esaudev.hackathonmoure.domain.usecase.GetSenderIdUseCase
import com.esaudev.hackathonmoure.domain.usecase.InitSenderIdUseCase
import com.esaudev.hackathonmoure.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val initSenderIdUseCase: InitSenderIdUseCase,
    private val getSenderIdUseCase: GetSenderIdUseCase
): ViewModel() {

    private val _senderIdState: MutableLiveData<Boolean> = MutableLiveData()
    val senderIdState: LiveData<Boolean>
        get() = _senderIdState

    init {
        initSenderIdUseCase()

        val senderId = getSenderIdUseCase()
        _senderIdState.value = senderId.isNotEmpty()
    }
}