package fr.epf.ratp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.epf.ratp.model.Ligne

@Database(entities= arrayOf(Ligne::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLigneDao() : LigneDao
}