package com.erickfelipebrittes.mobile.eventos.services.models

data class PokemonReturnListService (
    val count: Long? = null,
    val next: String? = null,
    val previous: Any? = null,
    val results: List<Result>? = null
)

data class Result (
    val name: String? = null,
    val url: String? = null
)
