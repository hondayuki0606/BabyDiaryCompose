package com.example.babydiarycompose.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.babydiarycompose.model.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Query("SELECT * FROM event where event.year_and_month_and_day = :currentData ORDER BY event.time_stamp ASC")
    fun getEvent(currentData: String): List<Event>

    @Query("UPDATE event SET year_and_month_and_day = :yearAndMonthAndDay, time_stamp = :timeStamp, time = :time, icon = :icon, event_name = :eventName, event_detail = :eventDetail WHERE uid = :uid")
    fun updateEvent(
        yearAndMonthAndDay: String,
        timeStamp: Long,
        time: String,
        icon: Int,
        eventName: String,
        eventDetail: String,
        uid: Int
    )

    @Insert
    fun insertAll(vararg event: Event)

    @Query("DELETE FROM event where event.uid = :uid ")
    fun delete(uid: Int)
}