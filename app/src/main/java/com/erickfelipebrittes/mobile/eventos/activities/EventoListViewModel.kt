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
import retrofit2.Response

class EventoListViewModel() : ViewModel() {

    val eventoListData = MutableLiveData<List<Evento>>()
    val isBusy = MutableLiveData<Boolean>()

    fun getData(context: Context) {
        viewModelScope.launch {
            setBusyStatus(true)
            withContext(Dispatchers.IO) {
                getEventos(context)
            }
            setBusyStatus(false)
        }
    }

    private fun setBusyStatus(isBusy: Boolean) {
        this.isBusy.value = isBusy
    }

    private fun getEventos(context: Context) {

        val backend = BackendService(context)

        val call = backend.getEventosService().getEventos()

        call.enqueue(object : retrofit2.Callback<List<Evento>> {
            override fun onResponse(
                call: retrofit2.Call<List<Evento>>,
                response: Response<List<Evento>>
            ) {
                if (response.isSuccessful()) {
                    eventoListData.value = response.body()
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Evento>>, t: Throwable) {
                eventoListData.value = null
            }
        })
    }
}