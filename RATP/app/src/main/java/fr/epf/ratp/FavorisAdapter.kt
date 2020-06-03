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


class FavorisAdapter(val favoris: List<Favoris>, val listener: (Favoris) -> Unit, val listener2: (Favoris) -> Unit) : RecyclerView.Adapter<FavorisAdapter.FavoriViewHolder>() {
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
        holder.favoriView.picto_imageview.setImageResource(
            if (favori.code == "1") R.drawable.m1
            else if (favori.code == "2") R.drawable.m2
            else if (favori.code == "3") R.drawable.m3
            else if (favori.code == "3b") R.drawable.m3bis
            else if (favori.code == "4") R.drawable.m4
            else if (favori.code == "5") R.drawable.m5
            else if (favori.code == "6") R.drawable.m6
            else if (favori.code == "7") R.drawable.m7
            else if (favori.code == "7b") R.drawable.m7bis
            else if (favori.code == "8") R.drawable.m8
            else if (favori.code == "9") R.drawable.m9
            else if (favori.code == "10") R.drawable.m10
            else if (favori.code == "11") R.drawable.m11
            else if (favori.code == "12") R.drawable.m12
            else if (favori.code == "13") R.drawable.m13
            else R.drawable.m14
        )
        holder.favoriView.fragment_main_item_delete.setOnClickListener {

            Log.d("EPF", "Delete")
            listener2(favori)
        }
        holder.bind(favori,listener)
    }
}