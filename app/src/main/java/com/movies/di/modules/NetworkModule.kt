package com.movies.di.modules

import com.google.gson.Gson
import com.movies.Constants
import com.movies.network.interceptor.HeadersInterceptor
import com.movies.network.interceptor.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *Created by marco on 2019-08-09
 */

@Module
class NetworkModule {

    @Provides
    fun okHttpClient(
            headersInterceptor: HeadersInterceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(headersInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(Constants.API.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(Constants.API.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(Constants.API.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    fun callFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun gson(): Gson {
        return Gson()
    }

    @Provides
    fun converterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun retrofit(
            okHttpClient: OkHttpClient,
            callFactory: CallAdapter.Factory,
            converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.API.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(callFactory)
                .addConverterFactory(converterFactory)
                .build()
    }
}