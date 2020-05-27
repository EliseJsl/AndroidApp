package fr.epf.ratp

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import fr.epf.ratp.data.*

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun AppCompatActivity.daoLigne(): LigneDao {
    val database = Room.databaseBuilder(this, AppDatabase::class.java,"RATP").build()

    return database.getLigneDao()
}

fun AppCompatActivity.daoStation(): StationDao{
    val database = Room.databaseBuilder(this, AppDatabase::class.java,"RATP").build()

    return database.getStationDao()
}
fun AppCompatActivity.daoSchedules(): ScheduleDao {
    val database = Room.databaseBuilder(this, AppDatabase::class.java,"RATP").build()

    return database.getScheduleDao()
}

fun AppCompatActivity.daoFavoris(): FavorisDao {
    val database = Room.databaseBuilder(this, AppDatabase::class.java,"RATP").build()

    return database.getFavorisDao()
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