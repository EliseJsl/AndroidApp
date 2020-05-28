package fr.epf.ratp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.ratp.model.Favoris


@Dao
interface FavorisDao {

    @Query("select * from favoris")
    suspend fun getFavoris() : List<Favoris>

    @Insert
    suspend fun addFavoris(favori: Favoris)

    @Delete
    suspend fun deleteFavoris(favori: Favoris)


    @Query("DELETE FROM favoris")
    suspend fun deleteAll()

}