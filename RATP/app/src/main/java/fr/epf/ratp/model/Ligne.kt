package fr.epf.ratp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "lignes")
data class Ligne(@PrimaryKey (autoGenerate = true) val idLigne: Int,
                 val code: String,
                 val name: String,
                 val directions: String,
                 val id: String) : Parcelable {
                  }