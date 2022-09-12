package com.esaudev.hackathonmoure.domain.model

import com.google.firebase.Timestamp
import java.time.LocalDate
import java.util.*

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val message: String = "",
    val senderId: String = "",
    val timestamp: Timestamp = Timestamp(Date())
)
