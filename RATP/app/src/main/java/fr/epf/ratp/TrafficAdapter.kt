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
    }

}