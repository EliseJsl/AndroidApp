package fr.epf.ratp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.ratp.model.Ligne
import kotlinx.android.synthetic.main.ligne_view.view.*


class LigneAdapter(val lignes: List<Ligne>, val listener: (Ligne) -> Unit) : RecyclerView.Adapter<LigneAdapter.LigneViewHolder>() {
        class LigneViewHolder(val ligneView: View) : RecyclerView.ViewHolder(ligneView){
            //fun bind(ligne: Ligne,clickListener: AdapterView.OnItemClickListener)
            fun bind(ligne: Ligne, listener: (Ligne) -> Unit) = with(ligneView)
            {
                    //ligneView.setOnClickListener {
                     //   listener.onItemClicked(ligne)
               // }

                ligneView.setOnClickListener { listener(ligne)}
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigneViewHolder{
            val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
            val view: View = layoutInflater.inflate(R.layout.ligne_view, parent, false)
            return LigneViewHolder(view)
        }

        override fun getItemCount(): Int = lignes.size

    override fun onBindViewHolder(holder: LigneViewHolder, position: Int) {
        val ligne = lignes[position]
        holder.ligneView.ligne_name_textview.text = ligne.name

        holder.bind(ligne,listener)

    }

}



