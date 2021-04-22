package com.erickfelipebrittes.mobile.eventos.activities

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erickfelipebrittes.mobile.eventos.services.BackendService
import com.erickfelipebrittes.mobile.eventos.services.models.PokemonReturnListService
import com.erickfelipebrittes.mobile.eventos.services.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokemonListViewModel() : ViewModel() {

    var pokemonElementListData = MutableLiveData<List<Result>>()
    val isBusy = MutableLiveData<Boolean>()

    fun getData(context: Context) {
        viewModelScope.launch {
            setBusyStatus(true)
            withContext(Dispatchers.IO) {
                getPokemonList(context)
            }
            setBusyStatus(false)
        }
    }

    private fun setBusyStatus(isBusy: Boolean) {
        this.isBusy.value = isBusy
    }

    private fun getPokemonList(context: Context) {

        val backend = BackendService(context)

        val call = backend.getPokemonListService().getPokemonsLimit()

        call.enqueue(object : retrofit2.Callback<PokemonReturnListService> {
            override fun onResponse(
                call: retrofit2.Call<PokemonReturnListService>,
                response: Response<PokemonReturnListService>
            ) {
                if (response.isSuccessful()) {
                    //TODO RESULTADO DOS POKEMONS LISTA
                    val result = response.body() as PokemonReturnListService
                    pokemonElementListData.value = result.results
                }
            }

            override fun onFailure(call: retrofit2.Call<PokemonReturnListService>, t: Throwable) {
                pokemonElementListData.value = null
            }
        })
    }
}