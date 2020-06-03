package fr.epf.ratp

import android.app.ProgressDialog.show
import android.content.Intent

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

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
        val ligne = intent.getStringExtra("Code")
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
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
        supportActionBar?.title=ligne

        if (code == "1") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFFFFBE00.toInt()))
        else if (code == "2") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF0055C8.toInt()))
        else if (code == "3") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF6E6E00.toInt()))
        else if (code == "3b") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF82C8E6.toInt()))
        else if (code == "4") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFFA0006E.toInt()))
        else if (code == "5") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFFFF5A00.toInt()))
        else if (code == "6") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF82DC73.toInt()))
        else if (code == "7") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFFFF82B4.toInt()))
        else if (code == "7b") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF82DC73.toInt()))
        else if (code == "8") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFFD282BE.toInt()))
        else if (code == "9") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFFD2D200.toInt()))
        else if (code == "10") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFFDC9600.toInt()))
        else if (code == "11") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF6E491E.toInt()))
        else if (code == "12") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF00643C.toInt()))
        else if (code == "13") supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF82C8E6.toInt()))
        else supportActionBar?.setBackgroundDrawable(ColorDrawable(0xFF640082.toInt()))
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


    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_station_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)  =
        when(item.itemId) {

            R.id.action_traffic -> {
                getTrafficMetro()
                true
            }
            else -> true
        }

    private fun getTrafficMetro(){
        var titre = "titre"
        var message = "RAS"

        val code = intent.getStringExtra("CodeLigne")
        val service = retrofit().create(LignesAPI::class.java)
        runBlocking {
            val traffics = code?.let { service.getTraffic("metros", it) }
            Log.d("EPF", "$traffics")
            if(traffics != null){
                titre = traffics.result.title
                message = traffics.result.message
            }
        }

        val intent = Intent(this, TrafficActivity::class.java).apply{
            putExtra("Titre", titre)
            putExtra("Message", message)
            putExtra("Code",code)
        }
        startActivity(intent)



        }
    }


