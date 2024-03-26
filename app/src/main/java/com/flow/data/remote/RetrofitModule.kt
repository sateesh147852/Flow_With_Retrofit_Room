package com.flow.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    fun getUserService(): UserService {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }
}