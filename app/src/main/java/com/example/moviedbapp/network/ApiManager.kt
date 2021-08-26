package com.example.moviedbapp.network

import com.example.moviedbapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class ApiManager {

    companion object {
        private var retrofit: Retrofit? = null
        private val loggingInterceptor = LoggingInterceptor()

        @Singleton
        private val okHttpClient: OkHttpClient
            get() {
                val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
                builder.addInterceptor(loggingInterceptor)
                builder.connectTimeout(30, TimeUnit.SECONDS)
                builder.readTimeout(90, TimeUnit.SECONDS)
                builder.writeTimeout(30, TimeUnit.SECONDS)
                return builder.build()
            }

        @Singleton
        fun getInstance(): Retrofit {
            retrofit?.let {
                return it
            } ?: return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}