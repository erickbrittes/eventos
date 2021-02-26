package com.erickfelipebrittes.mobile.eventos.services

import com.erickfelipebrittes.mobile.eventos.services.models.Evento
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventoService {

    @GET("api/events")
    fun getEventos(): Call<List<Evento>>

    @GET("api/events/{id}")
    fun getEventoId(@Path("id") id: Int): Call<Evento>
}