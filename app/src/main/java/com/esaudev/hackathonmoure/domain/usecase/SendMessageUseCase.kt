package com.esaudev.hackathonmoure.domain.usecase

import com.esaudev.hackathonmoure.domain.model.Message
import com.esaudev.hackathonmoure.domain.repository.MessagesRepository
import com.esaudev.hackathonmoure.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: MessagesRepository
) {
    suspend operator fun invoke(message: Message): Flow<Resource<Unit>> = flow {

        emit(Resource.Loading)

        val networkRequest = repository.sendMessage(message)

        when(networkRequest) {
            is Resource.Success -> emit(Resource.Success(Unit))
            is Resource.Error -> emit(Resource.Error(networkRequest.message))
            else -> Resource.Finished
        }

    }
}