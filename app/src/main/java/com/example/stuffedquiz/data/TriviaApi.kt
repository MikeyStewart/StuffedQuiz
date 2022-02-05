package com.example.stuffedquiz.data

import androidx.annotation.WorkerThread
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TriviaApi {

    @WorkerThread
    fun get(): TriviaApiService {
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .baseUrl(BASE_URL)
            .build()
            .create(TriviaApiService::class.java)
    }

    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }
}