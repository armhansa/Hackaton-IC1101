package com.example.myapplication.basic_api.Interceptor

import com.example.myapplication.basic_api.*
import okhttp3.Interceptor
import okhttp3.Response

class DeeplinkTransactionHeaderInterceptor : Interceptor {
    private lateinit var accessToken: String

    fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("resourceOwnerId", API_KEY)
                .addHeader("requestUId", UID)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("accept-language", LANGUAGE)
                .addHeader("channel", CHANNEL)
                .build()
        )
    }
}