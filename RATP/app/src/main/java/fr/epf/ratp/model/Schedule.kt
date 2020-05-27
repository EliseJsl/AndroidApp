package fr.epf.ratp.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "schedules")
data class Schedule (@PrimaryKey(autoGenerate = true) val idSchedule: Int,
                     val message: String,
                     val destination : String
) : Parcelable {

}