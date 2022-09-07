package com.esaudev.hackathonmoure.di

import com.esaudev.hackathonmoure.data.repository.MessagesRepositoryImpl
import com.esaudev.hackathonmoure.domain.repository.MessagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMessagesRepository(
        messagesRepository: MessagesRepositoryImpl
    ): MessagesRepository

}