package com.erickfelipebrittes.mobile.eventos.services.models

import java.io.Serializable

/**
 * Created by Érick Felipe Brittes on 21/02/21.
 */
data class Evento(
    val date: Long,
    val description: String,
    val id: Int,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val people: List<String>?,
    val price: Double,
    val title: String
) : Serializable