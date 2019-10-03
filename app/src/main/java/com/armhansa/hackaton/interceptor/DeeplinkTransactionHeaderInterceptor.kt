package com.armhansa.hackaton.interceptor

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
                .addHeader("Content-Type", "application/json")
                .addHeader("resourceOwnerId", "l7f031d768df40465ba05ae327022a5220")
                .addHeader("requestUId", "12345")
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("accept-language", "EN")
                .addHeader("channel", "scbeasy")
                .build()
        )
    }
}