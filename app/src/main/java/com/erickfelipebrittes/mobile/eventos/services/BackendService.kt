package com.erickfelipebrittes.mobile.eventos.services

import android.content.Context
import com.erickfelipebrittes.mobile.eventos.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BackendService(context: Context) {

    private val logging = HttpLoggingInterceptor()
    private val httpClient: OkHttpClient
    private val retrofit: Retrofit

    companion object {
        private const val SECONDS_TIMEOUT = 120L
    }

    init {

        val builder = OkHttpClient.Builder()
            .connectTimeout(SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(SECONDS_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            /** NÃO LOGAR DADOS EM PRODUÇÃO (questões de segurança) **/
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        builder.addInterceptor(AuthInterceptor(context))

        httpClient = builder.build()

        val converter = GsonBuilder().serializeNulls().create()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_SERVER)
            .addConverterFactory(GsonConverterFactory.create(converter))
            .client(httpClient)
            .build()
    }

    // Eventos
    fun getEventosService(): EventoService = retrofit.create(EventoService::class.java)
}