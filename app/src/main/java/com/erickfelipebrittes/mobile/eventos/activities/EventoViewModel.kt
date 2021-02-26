package com.erickfelipebrittes.mobile.eventos.activities

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erickfelipebrittes.mobile.eventos.services.BackendService
import com.erickfelipebrittes.mobile.eventos.services.models.Evento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class EventoViewModel() : ViewModel() {

    val eventoData = MutableLiveData<Evento>()
    val isBusy = MutableLiveData<Boolean>()

    fun getData(context: Context, idInt: Int) {
        viewModelScope.launch {
            setBusyStatus(true)
            withContext(Dispatchers.IO) {
                getEvento(context, idInt)
            }
            setBusyStatus(false)
        }
    }

    private fun setBusyStatus(isBusy: Boolean) {
        this.isBusy.value = isBusy
    }

    private fun getEvento(context: Context, idInt: Int) {

        val backend = BackendService(context)

        val call = backend.getEventosService().getEventoId(idInt)

        call.enqueue(object : retrofit2.Callback<Evento> {
            override fun onResponse(call: Call<Evento>, response: Response<Evento>) {
                if (response.isSuccessful()) {
                    eventoData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Evento>, t: Throwable) {
                eventoData.value = null
            }
        })
    }
}