package com.esaudev.hackathonmoure.domain.usecase

import javax.inject.Inject

class InitSenderIdUseCase @Inject constructor(
    private val getSenderIdUseCase: GetSenderIdUseCase,
    private val saveSenderIdUseCase: SaveSenderIdUseCase
) {

    operator fun invoke() {
        val senderId = getSenderIdUseCase()
        if (senderId.isEmpty()) {
            saveSenderIdUseCase()
        }
    }

}