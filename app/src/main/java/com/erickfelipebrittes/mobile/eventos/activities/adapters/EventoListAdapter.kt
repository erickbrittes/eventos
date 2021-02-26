package com.erickfelipebrittes.mobile.eventos.activities.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erickfelipebrittes.mobile.eventos.R
import com.erickfelipebrittes.mobile.eventos.activities.DetalhesActivity
import com.erickfelipebrittes.mobile.eventos.activities.MainActivity
import com.erickfelipebrittes.mobile.eventos.services.models.Evento
import com.erickfelipebrittes.mobile.eventos.utils.convertStringToCalendar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_evento.view.*


class EventoListAdapter(val activity: MainActivity) :
    RecyclerView.Adapter<EventoListAdapter.EventoListViewHolder>() {

    private val items = mutableListOf<Evento>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoListViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventoListViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<Evento>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: EventoListViewHolder, position: Int) {
        holder.configure(items[position])
    }

    inner class EventoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imagem = itemView.iv_imagem
        private val title = itemView.tv_title
        private val description = itemView.tv_description
        private val date = itemView.tv_date
        private val btDetails = itemView.bt_details

        fun configure(item: Evento) {
            Picasso.get()
                .load(item.image)
                .placeholder(R.drawable.ic_cloud_queue_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(imagem)

            title.text = item.title
            description.text = item.description
            date.text = convertStringToCalendar(item.date)

            btDetails.setOnClickListener {
                val intentDetalhes = Intent(activity, DetalhesActivity::class.java)
                intentDetalhes.putExtra(DetalhesActivity.KEY_EXTRA_ID, item.id)
                activity.startActivity(intentDetalhes)
            }
        }
    }
}