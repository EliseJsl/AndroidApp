package fr.epf.ratp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favoris")
data class Favoris (@PrimaryKey(autoGenerate = true) val idFavoris: Int,
                    val name: String,
                    val code: String
) : Parcelable {
}