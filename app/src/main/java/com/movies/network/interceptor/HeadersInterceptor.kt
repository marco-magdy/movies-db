package com.movies.network.interceptor

import com.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


/**
 *Created by marco on 2019-08-09
 */
class HeadersInterceptor
@Inject
constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.MovieDBApiKey)
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}