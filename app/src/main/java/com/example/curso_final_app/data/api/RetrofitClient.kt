package com.example.curso_final_app.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // Tiempo máximo para conectar (TCP handshake)
        .readTimeout(30, TimeUnit.SECONDS)    // Tiempo máximo para leer datos del servidor
        .writeTimeout(30, TimeUnit.SECONDS)   // Tiempo máximo para enviar datos al servidor
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}