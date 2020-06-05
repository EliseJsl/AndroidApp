package fr.epf.ratp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.ratp.model.Ligne
import fr.epf.ratp.service.LignesAPI
import kotlinx.android.synthetic.main.activity_all_traffic.*
import kotlinx.android.synthetic.main.activity_list_lignes.*
import kotlinx.coroutines.runBlocking

class TrafficAllActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_traffic)
        supportActionBar?.title="Informations trafic"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        traffics_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        synchroServer()


    }

    private fun synchroServer() {
        val service = retrofit().create(LignesAPI::class.java)
        var traffics : ArrayList<String> = ArrayList()
        runBlocking {
            val results = service.getAllTraffic("metros")
            Log.d("EPF", "$results")
            results.result.metros.map {
                traffics.add(it.message)
            }
        }
        traffics_recyclerview.adapter = TrafficAdapter(traffics)

    }

}