package fr.epf.ratp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.ratp.data.ScheduleDao
import fr.epf.ratp.data.StationDao
import fr.epf.ratp.model.Schedule
import fr.epf.ratp.model.Station
import fr.epf.ratp.service.LignesAPI
import kotlinx.android.synthetic.main.activity_detail_station.*
import kotlinx.android.synthetic.main.activity_list_stations.*
import kotlinx.coroutines.runBlocking

class ListStationsActivity : AppCompatActivity() {
    private var stationDao : StationDao? = null

    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_stations)
        stations_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        stationDao = daoStation()

        val code = intent.getStringExtra("CodeLigne")

        runBlocking {

            textView.text = code

        }

        synchroServer(code)



    }

    override fun onResume() { // refresh
        super.onResume()
        val code = intent.getStringExtra("CodeLigne")
        runBlocking {
            val stations = stationDao?.getStations()
            stations_recyclerview.adapter = StationAdapter(stations ?: emptyList()) { station : Station -> stationClicked(station,code) } // !! veut dire je t'assure ca sera pas null
            // ?: elvis operator : je te renvoie clients et si clients est vide je te renvoie une liste vide
        }

    }


    private fun synchroServer(code: String?) {
        val service = retrofit().create(LignesAPI::class.java)
        runBlocking {
            val stations = code?.let { service.getStations("metros", it) }

            Log.d("EPF", "$stations")
            stationDao?.deleteAll()
            if (stations != null) {
                stations.result.stations.map {
                    val station = Station(0, it.name, it.slug, "","","","")
                        val schedulesA = code?.let { service.getStationSchedules("metros", it, station.name, "A")}
                        val schedulesB = code?.let { service.getStationSchedules("metros", it, station.name, "R")}

                                val stationsFin = Station(0, station.name, station.slug, schedulesA.result.schedules[0].message,schedulesA.result.schedules[0].destination,schedulesB.result.schedules[0].message,schedulesB.result.schedules[0].destination )
                                stationDao?.addStation(stationsFin)


                    }
                }
                val liststations = stationDao?.getStations()
                stations_recyclerview.adapter = StationAdapter(
                    liststations ?: emptyList()
                ) { station: Station -> stationClicked(station, code) }
            }
        }


    private fun stationClicked(station: Station, code: String?){
        val intent = Intent(this, DetailStationActivity::class.java).apply{
            putExtra("Name", station.name)
            putExtra("Code", code)
        }
        startActivity(intent)
    }


}