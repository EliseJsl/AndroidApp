package fr.epf.ratp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.ratp.data.FavorisDao
import android.content.Intent
import android.util.Log
import fr.epf.ratp.model.Favoris
import fr.epf.ratp.model.Station
import fr.epf.ratp.service.LignesAPI
import kotlinx.android.synthetic.main.activity_list_favori.*

import kotlinx.coroutines.runBlocking

class AddFavoris : AppCompatActivity() {
    private var favorisDao : FavorisDao? = null

    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_favori)
        favori_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        favorisDao = daoFavoris()

        val code = intent.getStringExtra("CodeLigne")
        runBlocking{

        val liststations = favorisDao?.getFavoris()
        favori_recyclerview.adapter = FavorisAdapter(liststations ?: emptyList()) { favoris : Favoris -> stationClicked(favoris) }

        }


    }

    override fun onResume() { // refresh
        super.onResume()
        runBlocking {
            val favoris = favorisDao?.getFavoris()
            favori_recyclerview.adapter = FavorisAdapter(favoris ?: emptyList()) { favoris : Favoris -> stationClicked(favoris) } // !! veut dire je t'assure ca sera pas null
            // ?: elvis operator : je te renvoie clients et si clients est vide je te renvoie une liste vide
        }

    }

    private fun stationClicked(favoris: Favoris){
        val intent = Intent(this, DetailStationActivity::class.java).apply{
            putExtra("Name", favoris.name)
            putExtra("Code", favoris.code)
        }
        startActivity(intent)
    }


}