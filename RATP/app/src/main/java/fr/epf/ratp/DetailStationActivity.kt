package fr.epf.ratp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.ratp.data.FavorisDao
import fr.epf.ratp.data.ScheduleDao
import fr.epf.ratp.model.Favoris
import fr.epf.ratp.model.Schedule
import fr.epf.ratp.model.Station
import fr.epf.ratp.service.LignesAPI
import kotlinx.android.synthetic.main.activity_detail_station.*
import kotlinx.coroutines.runBlocking

class DetailStationActivity : AppCompatActivity() {
    private var scheduleDao : ScheduleDao? = null
    private var favorisDao: FavorisDao?= null


    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_station)
        schedule_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        scheduleDao = daoSchedules()
        favorisDao=daoFavoris()

        val name = intent.getStringExtra("Name")
        val code = intent.getStringExtra("Code")

        //  runBlocking {


        //      code_textview.text = "Prochain train dans"

        //  }


        synchroServer(name,code)


    }


    override fun onResume() { // refresh
        super.onResume()
        runBlocking {
            val schedule = scheduleDao?.getSchedule()
            schedule_recyclerview.adapter = ScheduleAdapter(schedule ?: emptyList()) // !! veut dire je t'assure ca sera pas null
            // ?: elvis operator : je te renvoie clients et si clients est vide je te renvoie une liste vide
        }

    }



    private fun synchroServer(name: String?, code: String?) {

        val service = retrofit().create(LignesAPI::class.java)
        runBlocking {
            val schedules = code?.let { name?.let { it1 ->
                service.getStationSchedules("metros", it,
                    it1,"A")
            } }

            Log.d("EPF", "$schedules")
            scheduleDao?.deleteAll()
            if (schedules != null) {
                schedules.result.schedules.map{
                    val schedule = Schedule(0, it.message, it.destination)
                    Log.d("EPF", "$schedule")
                    scheduleDao?.addSchedule(schedule)

                }
            }
            val listschedules = scheduleDao?.getSchedule()

            schedule_recyclerview.adapter = ScheduleAdapter(listschedules ?: emptyList())

        }

    }

    private  fun sauvegardeFavoris(){
        val name = intent.getStringExtra("Name")
        val code = intent.getStringExtra("Code")
        runBlocking {
            val favori= Favoris (0, name, code)

            val listFavori = favorisDao?.getFavoris()
            if (listFavori != null) {
                for(i in listFavori){
                    val nameFavoris= i.name
                    val codeFavoris= i.code

                    if ( nameFavoris != name && codeFavoris!= code)
                    {
                        favorisDao?.addFavoris(favori)
                        Log.d("EPF", "Ajout Favori")
                    }
                    else
                    {
                        Log.d("EPF", "Favori deja existant")
                    }
                }


            }


            Log.d("EPF", "$listFavori")

        }
    }


    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_station,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean =
        when(item.itemId){

            //  R.id.action_synchro ->{
            //      synchroServer(name,code)
            //      true
            //  }
            // R.id.action_settings ->{
            //      val intent = Intent(this, PreferencesActivity::class.java)
            //      startActivity(intent)
            //       true
            //  }
            R.id.action_favoris ->{
                sauvegardeFavoris()
                true
            }
            // }
            else -> onOptionsItemSelected(item)
        }
}
