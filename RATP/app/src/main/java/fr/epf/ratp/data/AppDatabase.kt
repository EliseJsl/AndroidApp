package fr.epf.ratp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.epf.ratp.model.Favoris
import fr.epf.ratp.model.Ligne
import fr.epf.ratp.model.Schedule
import fr.epf.ratp.model.Station

@Database(entities= arrayOf(Ligne::class, Station::class, Schedule::class, Favoris::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLigneDao() : LigneDao
    abstract fun getStationDao(): StationDao
    abstract fun getScheduleDao(): ScheduleDao
    abstract fun getFavorisDao(): FavorisDao

}