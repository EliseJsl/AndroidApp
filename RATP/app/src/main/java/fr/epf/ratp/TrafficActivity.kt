package fr.epf.ratp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_traffic.*

class TrafficActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic)
        val titre = intent.getStringExtra("Titre")
        val message = intent.getStringExtra("Message")

        traffic_title_textview.text = titre
        traffic_message_textview.text = message


    }
}