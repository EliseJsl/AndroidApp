package fr.epf.ratp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator


class QRCodeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        letsScan()

        findViewById<Button>(R.id.btn).setOnClickListener { letsScan() }
    }

    private fun letsScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("scan")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(scanResult.contents == "10 - Segur"){
            val intent = Intent(this, DetailStationActivity::class.java).apply{
                putExtra("Name", "Segur")
                putExtra("Code", "10")
            }
            startActivity(intent)
        }
        if(scanResult.contents == "6 - Nation"){
            val intent = Intent(this, DetailStationActivity::class.java).apply{
                putExtra("Name", "Nation")
                putExtra("Code", "6")
            }
            startActivity(intent)
        }
        if(scanResult.contents == "11 - Republique"){
            val intent = Intent(this, DetailStationActivity::class.java).apply{
                putExtra("Name", "Republique")
                putExtra("Code", "11")
            }
            startActivity(intent)
        }
        if(scanResult.contents == "5 - Gare du Nord"){
            val intent = Intent(this, DetailStationActivity::class.java).apply{
                putExtra("Name", "Gare du Nord")
                putExtra("Code", "5")
            }
            startActivity(intent)
        }
    }

}