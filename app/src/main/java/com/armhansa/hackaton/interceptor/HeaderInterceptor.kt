package com.armhansa.hackaton.interceptor

import com.armhansa.hackaton.service.HeaderInfo
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", HeaderInfo.ContentType.toString())
                .addHeader("resourceOwnerId", "l7f031d768df40465ba05ae327022a5220")
                .addHeader("requestUId", "12345")
                .addHeader("accept-language", "EN")
                .build()
        )
    }
}