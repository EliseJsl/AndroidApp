package fr.epf.ratp

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import fr.epf.ratp.data.AppDatabase
import fr.epf.ratp.data.LigneDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun AppCompatActivity.dao(): LigneDao {
    val database = Room.databaseBuilder(this, AppDatabase::class.java,"RATP").build()

    return database.getLigneDao()
}

fun AppCompatActivity.retrofit(): Retrofit {

    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(StethoInterceptor())   
        .build()



    return Retrofit.Builder()
        .baseUrl("https://api-ratp.pierre-grimaud.fr/v4/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
}