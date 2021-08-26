package com.aldev.pokemontcg.data.source.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServer {
    private val BASE_URL = "https://api.pokemontcg.io/v2/"
    private val API_KEY = "a19b9248-dbbb-4b6c-a04a-97f9a08974b9"

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .header("X-API-Key", API_KEY)
                .build()
            chain.proceed(newRequest)
        })
        addInterceptor(loggingInterceptor)
    }
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private fun getInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun getService(): ApiRequest = getInstance().create(ApiRequest::class.java)
}