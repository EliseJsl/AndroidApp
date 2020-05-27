package fr.epf.ratp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_lignes_button.setOnClickListener {
            val intent = Intent(this, ListLignesActivity::class.java)
            startActivity(intent)
        }

        list_favoris_button.setOnClickListener {
            val intent = Intent(this, AddFavoris::class.java)
            startActivity(intent)
        }
    }

}


