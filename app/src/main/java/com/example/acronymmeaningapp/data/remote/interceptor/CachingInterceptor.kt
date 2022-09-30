package com.example.acronymmeaningapp.data.remote.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CachingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request= chain.request()
        val originalResponse = chain.proceed(request)

        val cacheNotAllowed = request.header(CACHE_CONTROL) == NO_CACHE
        if (cacheNotAllowed) return originalResponse

        val cacheControl = CacheControl.Builder()
            .maxAge(MAX_AGE, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(CACHE_CONTROL, cacheControl.toString())
            .build()
    }

    companion object {
        private const val CACHE_CONTROL = "Cache-Control"
        private const val NO_CACHE = "no-cache"
        private const val MAX_AGE = 15
    }
}