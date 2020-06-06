package fr.epf.ratp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.ratp.model.Ligne

@Dao
interface LigneDao {

        @Query("select * from lignes")
        suspend fun getLignes() : List<Ligne>

        @Insert
        suspend fun addLigne(ligne: Ligne)


        @Delete
        suspend fun deleteStation(ligne: Ligne)

        @Query("DELETE FROM lignes")
        suspend fun deleteAll()

        @Delete
        suspend fun deleteLigne(ligne: Ligne)

}