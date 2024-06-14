package com.example.babydiarycompose.dao

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

    @Insert
    fun insertAll(vararg event: Event)

    @Query("DELETE FROM event where event.uid = :uid ")
    fun delete(uid: Int)
}