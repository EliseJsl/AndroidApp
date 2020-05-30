package fr.epf.ratp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.ratp.data.StationDao
import fr.epf.ratp.model.Station
import fr.epf.ratp.service.LignesAPI
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


            nomligne_imageView.setImageResource(
                if (code == "1") R.drawable.m1
                else if (code == "2") R.drawable.m2
                else if (code == "3") R.drawable.m3
                else if (code == "3b") R.drawable.m3bis
                else if (code == "4") R.drawable.m4
                else if (code == "5") R.drawable.m5
                else if (code == "6") R.drawable.m6
                else if (code == "7") R.drawable.m7
                else if (code == "7b") R.drawable.m7bis
                else if (code == "8") R.drawable.m8
                else if (code == "9") R.drawable.m9
                else if (code == "10") R.drawable.m10
                else if (code == "11") R.drawable.m11
                else if (code == "12") R.drawable.m12
                else if (code == "13") R.drawable.m13
                else R.drawable.m14
            )

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
                stations.result.stations.map{
                    val station = Station(0, it.name, it.slug)
                    stationDao?.addStation(station)

                }
            }
            val liststations = stationDao?.getStations()
            stations_recyclerview.adapter = StationAdapter(liststations ?: emptyList()) { station : Station -> stationClicked(station,code) }

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