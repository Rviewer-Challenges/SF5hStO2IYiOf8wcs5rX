package com.esaudev.hackathonmoure.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.hackathonmoure.domain.model.Message
import com.esaudev.hackathonmoure.domain.usecase.FetchMessageRealTimeUseCase
import com.esaudev.hackathonmoure.domain.usecase.GetSenderIdUseCase
import com.esaudev.hackathonmoure.domain.usecase.SendMessageUseCase
import com.esaudev.hackathonmoure.util.Resource
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAllMessagesUseCase: FetchMessageRealTimeUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getSenderIdUseCase: GetSenderIdUseCase
): ViewModel() {

    private val _messagesListState: MutableLiveData<Resource<List<Message>>> = MutableLiveData()
    val messagesListState: LiveData<Resource<List<Message>>>
        get() = _messagesListState

    private val _sendMessageState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val sendMessageState: LiveData<Resource<Unit>>
        get() = _sendMessageState

    private val _senderId: MutableLiveData<String> = MutableLiveData()
    val senderId: LiveData<String>
        get() = _senderId

    init {
        getSenderId()
        getMessages()
    }

    private fun getMessages() {
        viewModelScope.launch {
            getAllMessagesUseCase().onEach {
                _messagesListState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            sendMessageUseCase(message).onEach {
                _sendMessageState.value = it
            }.launchIn(viewModelScope)
        }
    }

    private fun getSenderId() {
        viewModelScope.launch {
            _senderId.value = getSenderIdUseCase()
        }
    }
}