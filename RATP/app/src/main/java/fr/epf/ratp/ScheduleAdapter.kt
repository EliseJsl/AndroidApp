package fr.epf.ratp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.epf.ratp.model.Schedule
import kotlinx.android.synthetic.main.schedule_view.view.*


class ScheduleAdapter(val schedules: List<Schedule>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(val scheduleView: View) : RecyclerView.ViewHolder(scheduleView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder{
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.schedule_view, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]
        holder.scheduleView.schedule_name_textview.text = schedule.message
    }
}