package com.example.myapplication.basic_api.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class SCBManager {
    companion object {
        const val BASE_SCB_API = "https://api.partners.scb/partners/sandbox/"

       var client = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build();
    }

    fun createService(): SCBApiService =
        Retrofit.Builder()
            .baseUrl(BASE_SCB_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
            .run { create(SCBApiService::class.java) }

}


