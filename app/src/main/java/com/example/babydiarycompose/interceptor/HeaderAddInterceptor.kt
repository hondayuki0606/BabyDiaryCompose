package com.example.babydiarycompose.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.userAgent

class HeaderAddInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val header = request.newBuilder()
            .header("User-Agent", userAgent)
            .build()
        return chain.proceed(header)
    }
}