package com.erickfelipebrittes.mobile.eventos.activities.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erickfelipebrittes.mobile.eventos.R
import com.erickfelipebrittes.mobile.eventos.activities.DetalhesActivity
import com.erickfelipebrittes.mobile.eventos.activities.MainActivity
import com.erickfelipebrittes.mobile.eventos.services.models.Result
import kotlinx.android.synthetic.main.item_evento.view.*


class PokemonResulListAdapter(val activity: MainActivity) :
    RecyclerView.Adapter<PokemonResulListAdapter.EventoListViewHolder>() {

    private val items = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoListViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventoListViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<Result>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: EventoListViewHolder, position: Int) {
        holder.configure(items[position])
    }

    inner class EventoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imagem = itemView.details_iv_photo
        private val title = itemView.details_tv_name
        private val description = itemView.details_tv_number
        private val date = itemView.details_tv_stats
        private val btDetails = itemView.bt_details

        fun configure(item: Result) {

            title.text = item.name
            description.text = item.url

            btDetails.setOnClickListener {
                val intentDetalhes = Intent(activity, DetalhesActivity::class.java)
                intentDetalhes.putExtra(DetalhesActivity.KEY_EXTRA_ID, item.url)
                activity.startActivity(intentDetalhes)
            }
        }
    }
}