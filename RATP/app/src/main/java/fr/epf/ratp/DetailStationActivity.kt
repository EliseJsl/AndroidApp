package fr.epf.ratp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.ratp.data.FavorisDao
import fr.epf.ratp.data.ScheduleDao
import fr.epf.ratp.model.Favoris
import fr.epf.ratp.model.Schedule
import fr.epf.ratp.service.LignesAPI
import kotlinx.android.synthetic.main.activity_detail_station.*
import kotlinx.coroutines.runBlocking


class DetailStationActivity : AppCompatActivity() {
    private var scheduleDao : ScheduleDao? = null
    private var favorisDao: FavorisDao?= null


    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_station)

        scheduleA_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        scheduleB_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        scheduleDao = daoSchedules()
        favorisDao = daoFavoris()


        val name = intent.getStringExtra("Name")
        val code = intent.getStringExtra("Code")
         synchroServer(name,code)
        supportActionBar?.title = name

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

    }


    override fun onResume() { // refresh
        super.onResume()
        runBlocking {
            val name = intent.getStringExtra("Name")
            val code = intent.getStringExtra("Code")
            synchroServer(name,code)

        }

    }



    private fun synchroServer(name: String?, code: String?) {

        val service = retrofit().create(LignesAPI::class.java)
        runBlocking {
            val schedulesA = code?.let { name?.let { it1 ->
                service.getStationSchedules("metros", it,
                    it1,"A")
            } }

            val schedulesR = code?.let { name?.let { it1 ->
                service.getStationSchedules("metros", it,
                    it1,"R")
            } }
            scheduleDao?.deleteAll()

            if (schedulesA != null) {
                schedulesA.result.schedules.map{
                    val schedule = Schedule(0, it.message, it.destination)
                    scheduleDao?.addSchedule(schedule)
                    allerDirection_textview.text = schedule.destination
                }
            }
            val listschedulesA = scheduleDao?.getSchedule()
            scheduleDao?.deleteAll()
            if (schedulesR != null) {
                schedulesR.result.schedules.map{
                    val schedule = Schedule(0, it.message, it.destination)
                    scheduleDao?.addSchedule(schedule)
                    retourDirection_textview.text = schedule.destination
                }
            }
            val listschedulesR = scheduleDao?.getSchedule()

            scheduleA_recyclerview.adapter = ScheduleAdapter(listschedulesA ?: emptyList())
            scheduleB_recyclerview.adapter = ScheduleAdapter(listschedulesR ?: emptyList())

        }

    }

    private  fun sauvegardeFavoris(){
        val name = intent.getStringExtra("Name")
        val code = intent.getStringExtra("Code")
        runBlocking {
            val favori= Favoris (0, name, code)
           favorisDao?.addFavoris(favori)
        }
    }


    private  fun deleteFavoris(id:Int)
    {
        val name = intent.getStringExtra("Name")
        val code = intent.getStringExtra("Code")

        runBlocking {
            val favori= Favoris (id, name, code)
            favorisDao?.deleteFavoris(favori)


        }
    }


    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_station,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem)  =
        when(item.itemId){

             R.id.action_synchro ->{
                 onResume()
               true
             }


            R.id.action_favoris ->{
                AlertDialog.Builder(this).apply {
                    val name = intent.getStringExtra("Name")
                    val code = intent.getStringExtra("Code")
                    runBlocking {
                        val listFavori = favorisDao?.getFavoris()

                        if (listFavori != null) {
                            if (listFavori.size > 0) {
                                var compteur = 0
                                for (i in listFavori) {
                                    val nameFavoris = i.name
                                    val codeFavoris = i.code

                                    if (name == nameFavoris && code == codeFavoris) {
                                        setTitle(R.string.Delete_favori)
                                        setNegativeButton(android.R.string.no){ _,_ ->
                                            Log.d("EPF", "Supprimé")
                                        }
                                        setPositiveButton(android.R.string.yes){
                                                _,_ ->
                                            deleteFavoris(i.idFavoris)
                                        }
                                        break
                                    } else {
                                        compteur = compteur + 1
                                        if (compteur == listFavori.size) {
                                            setTitle(R.string.Ajout_favori)
                                            setNegativeButton(android.R.string.no){ _,_ ->
                                                Log.d("EPF", "Ajouté")
                                            }
                                            setPositiveButton(android.R.string.yes){
                                                    _,_ ->

                                                sauvegardeFavoris()


                                            }

                                        }
                                    }
                                }
                                //
                            } else {
                                setTitle(R.string.Ajout_favori)
                                setNegativeButton(android.R.string.no){ _,_ ->
                                    Log.d("EPF", "Non supprimé")
                                }
                                setPositiveButton(android.R.string.yes){
                                        _,_ ->

                                    sauvegardeFavoris()


                                }
                            }


                        }
                    }
                    show()
                }
                    true

            }
            // }
            else -> true
        }
}
