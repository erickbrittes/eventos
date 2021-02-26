package com.erickfelipebrittes.mobile.eventos.services

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()
        val response = chain.proceed(requestBuilder.build())
//        val result = response.body!!.string()
        return response
    }
}