package com.erickfelipebrittes.mobile.eventos.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.erickfelipebrittes.mobile.eventos.R
import com.erickfelipebrittes.mobile.eventos.activities.adapters.PokemonResulListAdapter
import com.erickfelipebrittes.mobile.eventos.services.models.Result
import com.erickfelipebrittes.mobile.eventos.utils.hide
import com.erickfelipebrittes.mobile.eventos.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_loading.*

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonListViewModel: PokemonListViewModel
    private lateinit var adapter: PokemonResulListAdapter

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

        adapter = PokemonResulListAdapter(this)
        rv_eventos.layoutManager = LinearLayoutManager(this)
        rv_eventos.adapter = this.adapter
    }

    private fun initViewModel() {
        pokemonListViewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        pokemonListViewModel.getData(this)
    }

    private val pokemonListObserver = Observer<List<Result>> {
        it.let { it1 -> adapter.updateList(it1) }
    }

    private fun registerObservers() {
        pokemonListViewModel.isBusy.observe(this, loadingObserver)
        pokemonListViewModel.pokemonElementListData.observe(this, pokemonListObserver)
    }
}