package fr.epf.ratp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.epf.ratp.model.Ligne
import fr.epf.ratp.model.Station

@Database(entities= arrayOf(Ligne::class, Station::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLigneDao() : LigneDao
    abstract fun getStationDao(): StationDao
}