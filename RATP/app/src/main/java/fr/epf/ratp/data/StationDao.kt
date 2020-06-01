package fr.epf.ratp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.ratp.model.Station


@Dao
interface StationDao {
    @Query("select * from stations")
    suspend fun getStations() : List<Station>

    @Insert
    suspend fun addStation(station: Station)

    @Query("DELETE FROM stations")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteStation(station: Station)
}