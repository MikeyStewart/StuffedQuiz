package com.example.stuffedquiz.di

import com.example.stuffedquiz.data.TriviaApi
import com.example.stuffedquiz.data.TriviaDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideTriviaDataSource(): TriviaDataSource {
        return TriviaDataSource(TriviaApi().get())
    }
}