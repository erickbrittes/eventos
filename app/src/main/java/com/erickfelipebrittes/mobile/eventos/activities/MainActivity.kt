package com.erickfelipebrittes.mobile.eventos.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.erickfelipebrittes.mobile.eventos.R
import com.erickfelipebrittes.mobile.eventos.activities.adapters.EventoListAdapter
import com.erickfelipebrittes.mobile.eventos.services.models.Evento
import com.erickfelipebrittes.mobile.eventos.utils.hide
import com.erickfelipebrittes.mobile.eventos.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_loading.*

class MainActivity : AppCompatActivity() {

    private lateinit var eventoListViewModel: EventoListViewModel
    private lateinit var adapter: EventoListAdapter
    val isBusy = MutableLiveData<Boolean>()
    private val loadingObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            containerLoading.show()
        } else {
            containerLoading.hide()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {

        initViewModel()
        registerObservers()

        adapter = EventoListAdapter(this)
        rv_eventos.layoutManager = LinearLayoutManager(this)
        rv_eventos.adapter = this.adapter
    }


    private fun initViewModel() {
        eventoListViewModel = ViewModelProvider(this).get(EventoListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        eventoListViewModel.getData(this)
    }

    private val eventoObserver = Observer<List<Evento>> {
        adapter.updateList(it)
    }

    private fun registerObservers() {
        eventoListViewModel.isBusy.observe(this, loadingObserver)
        eventoListViewModel.eventoListData.observe(this, eventoObserver)
    }
}