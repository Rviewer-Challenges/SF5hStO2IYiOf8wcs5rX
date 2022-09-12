package com.esaudev.hackathonmoure.data.repository

import com.esaudev.hackathonmoure.data.remote.Constants.DEFAULT_NETWORK_ERROR
import com.esaudev.hackathonmoure.data.remote.Constants.MESSAGES_COLLECTION
import com.esaudev.hackathonmoure.domain.model.Message
import com.esaudev.hackathonmoure.domain.repository.MessagesRepository
import com.esaudev.hackathonmoure.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): MessagesRepository {

    override suspend fun sendMessage(message: Message): Resource<Unit> {
        return try {
            var isSuccessful = false
            firebaseFirestore.collection(MESSAGES_COLLECTION)
                .document(message.id)
                .set(message, SetOptions.merge())
                .addOnCompleteListener { isSuccessful = it.isSuccessful }
                .await()

            if (isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error(DEFAULT_NETWORK_ERROR)
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}