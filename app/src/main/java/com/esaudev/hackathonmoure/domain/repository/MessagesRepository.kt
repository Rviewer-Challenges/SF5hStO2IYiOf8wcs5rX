package com.esaudev.hackathonmoure.domain.repository

import com.esaudev.hackathonmoure.domain.model.Message
import com.esaudev.hackathonmoure.util.Resource

interface MessagesRepository {

    suspend fun sendMessage(message: Message): Resource<Unit>

}