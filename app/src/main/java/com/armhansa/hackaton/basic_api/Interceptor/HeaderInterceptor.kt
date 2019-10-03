package com.example.myapplication.basic_api.Interceptor

import com.example.myapplication.basic_api.API_KEY
import com.example.myapplication.basic_api.CONTENT_TYPE
import com.example.myapplication.basic_api.LANGUAGE
import com.example.myapplication.basic_api.UID
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("resourceOwnerId", API_KEY)
                .addHeader("requestUId", UID)
                .addHeader("accept-language", LANGUAGE)
                .build()
        )
    }
}