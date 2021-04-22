package com.erickfelipebrittes.mobile.eventos.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.erickfelipebrittes.mobile.eventos.R
import com.erickfelipebrittes.mobile.eventos.services.models.PokemonElement
import com.erickfelipebrittes.mobile.eventos.utils.hide
import com.erickfelipebrittes.mobile.eventos.utils.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*
import kotlinx.android.synthetic.main.include_loading.*

/**
 * Created by Érick Felipe Brittes on 25/02/21.
 */
class DetalhesActivity : AppCompatActivity() {

    companion object {
        const val KEY_EXTRA_ID = "KEY_EXTRA_ID"
    }

    private lateinit var pokemonViewModel: PokemonViewModel

    private val loadingObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            containerLoading.show()
        } else {
            containerLoading.hide()
        }
    }

    var urlString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        if (intent.hasExtra(KEY_EXTRA_ID)) {
            urlString = intent.getStringExtra(KEY_EXTRA_ID)
        }

        setupView()
    }

    private fun setupView() {

        initViewModel()
        registerObservers()
    }

    private fun initViewModel() {
        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        pokemonViewModel.getData(this, urlString!!)
    }

    private val eventoObserver = Observer<PokemonElement> {

        it.sprites.let {
            it.let { sprites ->
                sprites?.front_default.let {
                    Picasso.get()
                        .load(sprites?.front_default)
                        .placeholder(R.drawable.ic_cloud_queue_black_24dp)
                        .error(R.drawable.ic_error_outline_black_24dp)
                        .into(details_iv_photo)
                }
            }
        }

        details_tv_name.text = it.name
        details_tv_number.text = it.id.toString()

        if (!it.stats.isNullOrEmpty()) {

            var statsString = ""

            val stats = it.stats.first()

            var lineString = getString(R.string.label_base_stat, stats.base_stat)

            lineString += "  ${getString(R.string.label_effort, stats.effort)}"

            lineString += "  ${getString(R.string.label_stat, stats.stat?.name)}"

            statsString += "  ${lineString}"

            details_tv_stats.text = statsString
        }

        details_tv_weight.text = getString(R.string.label_weight, it.weight)

        if (!it.types.isNullOrEmpty()) {
            details_tv_type.text = getString(R.string.label_type, it.types.first().type)
        }

        bt_confirm.setOnClickListener {
            val intentDetalhes = Intent(this, DetalhesActivity::class.java)
            intentDetalhes.putExtra(DetalhesActivity.KEY_EXTRA_ID, it.id)
            startActivity(intentDetalhes)
        }
    }

    private fun registerObservers() {
        pokemonViewModel.isBusy.observe(this, loadingObserver)
        pokemonViewModel.eventoData.observe(this, eventoObserver)
    }
}