package com.erickfelipebrittes.mobile.eventos.activities

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erickfelipebrittes.mobile.eventos.services.BackendService
import com.erickfelipebrittes.mobile.eventos.services.models.PokemonElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class PokemonViewModel() : ViewModel() {

    val eventoData = MutableLiveData<PokemonElement>()
    val isBusy = MutableLiveData<Boolean>()

    fun getData(context: Context, urlString: String) {
        viewModelScope.launch {
            setBusyStatus(true)
            withContext(Dispatchers.IO) {
                getPokemon(context, urlString)
            }
            setBusyStatus(false)
        }
    }

    private fun setBusyStatus(isBusy: Boolean) {
        this.isBusy.value = isBusy
    }

    private fun getPokemon(context: Context, urlString: String) {

        val backend = BackendService(context)

        val stringId = urlString.split("/")

        val call = backend.getPokemonListService().getPokemonId(stringId[stringId.size - 2].toInt())

        call.enqueue(object : retrofit2.Callback<PokemonElement> {
            override fun onResponse(
                call: Call<PokemonElement>,
                response: Response<PokemonElement>
            ) {
                if (response.isSuccessful()) {
                    eventoData.value = response.body()
                }
            }

            override fun onFailure(call: Call<PokemonElement>, t: Throwable) {
                eventoData.value = null
            }
        })
    }
}