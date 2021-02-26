package com.erickfelipebrittes.mobile.eventos.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.erickfelipebrittes.mobile.eventos.R
import com.erickfelipebrittes.mobile.eventos.services.models.Evento
import com.erickfelipebrittes.mobile.eventos.utils.convertStringToCalendar
import com.erickfelipebrittes.mobile.eventos.utils.formatReal
import com.erickfelipebrittes.mobile.eventos.utils.hide
import com.erickfelipebrittes.mobile.eventos.utils.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*
import kotlinx.android.synthetic.main.activity_detalhes.iv_imagem
import kotlinx.android.synthetic.main.activity_detalhes.tv_date
import kotlinx.android.synthetic.main.activity_detalhes.tv_description
import kotlinx.android.synthetic.main.activity_detalhes.tv_title
import kotlinx.android.synthetic.main.include_loading.*

/**
 * Created by Érick Felipe Brittes on 25/02/21.
 */
class DetalhesActivity : AppCompatActivity() {

    companion object {
        const val KEY_EXTRA_ID = "KEY_EXTRA_ID"
    }

    private lateinit var eventoViewModel: EventoViewModel

    val eventoData = MutableLiveData<Evento>()
    val isBusy = MutableLiveData<Boolean>()

    private val loadingObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            containerLoading.show()
        } else {
            containerLoading.hide()
        }
    }

    var idInt: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        if (intent.hasExtra(KEY_EXTRA_ID)) {
            idInt = intent.getIntExtra(KEY_EXTRA_ID, 0)
        }

        setupView()
    }

    private fun setupView() {

        initViewModel()
        registerObservers()
    }


    private fun initViewModel() {
        eventoViewModel = ViewModelProvider(this).get(EventoViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        eventoViewModel.getData(this, idInt!!)
    }

    private val eventoObserver = Observer<Evento> {

        Picasso.get()
            .load(it.image)
            .placeholder(R.drawable.ic_cloud_queue_black_24dp)
            .error(R.drawable.ic_error_outline_black_24dp)
            .into(iv_imagem)

        tv_title.text = it.title
        tv_description.text = it.description
        tv_date.text = convertStringToCalendar(it.date)
        tv_price.text = formatReal(it.price.toLong())

        bt_confirm.setOnClickListener {
            val intentDetalhes = Intent(this, DetalhesActivity::class.java)
            intentDetalhes.putExtra(DetalhesActivity.KEY_EXTRA_ID, it.id)
            startActivity(intentDetalhes)
        }
    }

    private fun registerObservers() {
        eventoViewModel.isBusy.observe(this, loadingObserver)
        eventoViewModel.eventoData.observe(this, eventoObserver)
    }
}