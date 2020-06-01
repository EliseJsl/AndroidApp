package fr.epf.ratp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.traffic_view.view.*

class TrafficAdapter(val traffics: List<String>) : RecyclerView.Adapter<TrafficAdapter.TrafficViewHolder>(){

    class TrafficViewHolder(val trafficView: View) : RecyclerView.ViewHolder(trafficView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrafficViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.traffic_view, parent, false)
        return TrafficViewHolder(view)

    }

    override fun getItemCount(): Int = traffics.size

    override fun onBindViewHolder(holder: TrafficViewHolder, position: Int) {
        val traffic = traffics[position]
        holder.trafficView.message_textview.text = traffic
        holder.trafficView.picto_imageview.setImageResource(
            when(position){
                0 -> R.drawable.m1
                1 -> R.drawable.m2
                2 -> R.drawable.m3
                3 -> R.drawable.m3bis
                4 -> R.drawable.m4
                5 -> R.drawable.m5
                6 -> R.drawable.m6
                7 -> R.drawable.m7
                8 -> R.drawable.m7bis
                9 -> R.drawable.m8
                10 -> R.drawable.m9
                11 -> R.drawable.m10
                12 -> R.drawable.m11
                13 -> R.drawable.m12
                14 -> R.drawable.m13
                15 -> R.drawable.m14
                else -> R.drawable.m14
            }

        )
    }

}