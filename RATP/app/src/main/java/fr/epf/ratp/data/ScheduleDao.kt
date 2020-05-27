package fr.epf.ratp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.epf.ratp.model.Schedule


@Dao
interface ScheduleDao {
    @Query("select * from schedules")
    suspend fun getSchedule() : List<Schedule>

    @Insert
    suspend fun addSchedule(schedule: Schedule)

    @Query("DELETE FROM schedules")
    suspend fun deleteAll()
}