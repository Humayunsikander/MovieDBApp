package com.example.moviedbapp.network

import android.util.Log
import com.example.moviedbapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)

        if (BuildConfig.DEBUG) {
            Log.d(
                "TAG",
                "Sending request url => $request.url()"
            )
            Log.d(
                "TAG",
                "Received response =>  is Success : ${response.isSuccessful} and ${response.body()}",
            )
        }

        return response
    }
}