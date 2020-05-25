package fr.epf.ratp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.ratp.data.LigneDao
import fr.epf.ratp.model.Ligne
import fr.epf.ratp.service.LignesAPI
import kotlinx.android.synthetic.main.activity_list_lignes.*
import kotlinx.android.synthetic.main.ligne_view.*
import kotlinx.coroutines.runBlocking


class ListLignesActivity : AppCompatActivity() {

    private var ligneDao : LigneDao? = null


    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_lignes)

        lignes_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ligneDao = daoLigne()

        synchroServer()


    }

    override fun onResume() { // refresh
        super.onResume()
        runBlocking {
            val lignes = ligneDao?.getLignes()
            lignes_recyclerview.adapter = LigneAdapter(lignes ?: emptyList()) { ligne : Ligne -> ligneClicked(ligne) } // !! veut dire je t'assure ca sera pas null
            // ?: elvis operator : je te renvoie clients et si clients est vide je te renvoie une liste vide
        }

    }


    private fun synchroServer() {
        val service = retrofit().create(LignesAPI::class.java)

        runBlocking {
            val lignes = service.getLignes("metros")
            Log.d("EPF", "$lignes")
            ligneDao?.deleteAll()
            lignes.result.metros.map{
                val ligne = Ligne(0, it.code, it.name, it.directions, it.id)
                ligneDao?.addLigne(ligne)
            }
            val listlignes = ligneDao?.getLignes()
            lignes_recyclerview.adapter = LigneAdapter(listlignes ?: emptyList()) { ligne : Ligne -> ligneClicked(ligne) }

        }

    }


    private fun ligneClicked(ligne: Ligne){
        val intent = Intent(this, ListStationsActivity::class.java).apply{
            putExtra("CodeLigne", ligne.code)
        }
        startActivity(intent)
    }

}