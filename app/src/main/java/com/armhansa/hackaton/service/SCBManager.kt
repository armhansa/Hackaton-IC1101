package com.example.myapplication.basic_api.service

import com.armhansa.hackaton.interceptor.DeeplinkTransactionHeaderInterceptor
import com.armhansa.hackaton.service.SCBApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SCBManager {

    companion object {
        const val BASE_SCB_API = "https://api.partners.scb/partners/sandbox/"

        var client = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
    }

    private lateinit var deeplinkTS: OkHttpClient

    fun createService(): SCBApiService =
        Retrofit.Builder()
            .baseUrl(BASE_SCB_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
            .run { create(SCBApiService::class.java) }

    fun setHeaderDeeplinkTransactionService(accessToken: String) {

        val dl: DeeplinkTransactionHeaderInterceptor = DeeplinkTransactionHeaderInterceptor();
        dl.setAccessToken(accessToken)
        deeplinkTS = OkHttpClient.Builder().addInterceptor(dl).build()
        println("ningnananoii > dl : $dl")
    }

    fun createDeeplinkTransactionService(): SCBApiService = Retrofit.Builder()
        .baseUrl(BASE_SCB_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(deeplinkTS).build()
        .run { create(SCBApiService::class.java) }

}


