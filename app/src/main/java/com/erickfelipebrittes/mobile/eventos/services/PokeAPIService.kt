package com.erickfelipebrittes.mobile.eventos.services

import com.erickfelipebrittes.mobile.eventos.services.models.PokemonElement
import com.erickfelipebrittes.mobile.eventos.services.models.PokemonReturnListService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeAPIService {

    //    pokemon?limit=100&offset=200
    @GET("pokemon?limit=151&offset=0")
    fun getPokemonsLimit(): Call<PokemonReturnListService>

    @GET("pokemon/{id}")
    fun getPokemonId(@Path("id") id: Int): Call<PokemonElement>
}