package fr.epf.ratp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "stations")
data class Station (@PrimaryKey(autoGenerate = true) val idStation: Int,
                    val name: String,
                    val slug: String,
                    val horaireAller: String,
                    val directionAller: String,
                    val horaireRetour: String,
                    val directionRetour: String
) : Parcelable{

}