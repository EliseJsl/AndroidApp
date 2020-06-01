package fr.epf.ratp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.epf.ratp.data.FavorisDao
import fr.epf.ratp.model.Favoris
import kotlinx.android.synthetic.main.favori_view.view.*

import kotlinx.android.synthetic.main.schedule_view.view.*
import kotlinx.coroutines.runBlocking


class FavorisAdapter(val favoris: List<Favoris>, val listener: (Favoris) -> Unit) : RecyclerView.Adapter<FavorisAdapter.FavoriViewHolder>() {
    class FavoriViewHolder(val favoriView: View) : RecyclerView.ViewHolder(favoriView){
        fun bind(favori: Favoris, listener: (Favoris) -> Unit) = with(favoriView)
        {
            //ligneView.setOnClickListener {
            //   listener.onItemClicked(ligne)
            // }

            favoriView.setOnClickListener { listener(favori)}
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriViewHolder{
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.favori_view, parent, false)
        return FavoriViewHolder(view)
    }

    override fun getItemCount(): Int = favoris.size

    override fun onBindViewHolder(holder: FavoriViewHolder, position: Int) {

        val favori = favoris[position]
        holder.favoriView.favori_name_textview.text = favori.name
        holder.favoriView.fragment_main_item_delete.setOnClickListener {


            Log.d("EPF", "Delete")

            holder.bind(favori,listener)
        }
        holder.bind(favori,listener)
    }
}