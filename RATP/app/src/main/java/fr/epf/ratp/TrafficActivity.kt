package fr.epf.ratp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_traffic.*
import kotlinx.coroutines.runBlocking

class TrafficActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { // permet d'afficher la liste
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic)
        val titre = intent.getStringExtra("Titre")
        val message = intent.getStringExtra("Message")
        val code=intent.getStringExtra("Code")
        supportActionBar?.title="Informations traffic"
        traffic_title_textview.text = "Etat du traffic: ${titre}"
        traffic_message_textview.text = "Informations complémentaires: ${message}"

        runBlocking {


            nomlignef_imageView.setImageResource(
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
}