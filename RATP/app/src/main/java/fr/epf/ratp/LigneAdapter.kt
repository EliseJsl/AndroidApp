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
        holder.ligneView.ligne_name_textview.text = "${ligne.directions}"

        holder.ligneView.ligne_imageview.setImageResource(
            if (ligne.code == "1") R.drawable.m1
            else if (ligne.code == "2") R.drawable.m2
            else if (ligne.code == "3") R.drawable.m3
            else if (ligne.code == "3b") R.drawable.m3bis
            else if (ligne.code == "4") R.drawable.m4
            else if (ligne.code == "5") R.drawable.m5
            else if (ligne.code == "6") R.drawable.m6
            else if (ligne.code == "7") R.drawable.m7
            else if (ligne.code == "7b") R.drawable.m7bis
            else if (ligne.code == "8") R.drawable.m8
            else if (ligne.code == "9") R.drawable.m9
            else if (ligne.code == "10") R.drawable.m10
            else if (ligne.code == "11") R.drawable.m11
            else if (ligne.code == "12") R.drawable.m12
            else if (ligne.code == "13") R.drawable.m13
            else R.drawable.m14
        )

        holder.bind(ligne,listener)

    }

}



