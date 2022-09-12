package com.esaudev.hackathonmoure.domain.usecase

import com.esaudev.hackathonmoure.data.remote.Constants.MESSAGES_COLLECTION
import com.esaudev.hackathonmoure.domain.model.Message
import com.esaudev.hackathonmoure.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FetchMessageRealTimeUseCase @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend operator fun invoke(): Flow<Resource<List<Message>>> = callbackFlow {

        val event = firestore.collection(MESSAGES_COLLECTION).orderBy("timestamp", Query.Direction.DESCENDING)

        val subscription = event.addSnapshotListener { snapshot, error ->

            if (error != null) {
                this.trySend(Resource.Error(error.message.toString())).isSuccess
                return@addSnapshotListener
            }

            if (snapshot != null) {

                val noteList = snapshot.toObjects(Message::class.java)

                this.trySend(Resource.Success(noteList)).isSuccess

            }
        }

        awaitClose { subscription.remove() }

    }

}