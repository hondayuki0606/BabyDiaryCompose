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

    @Insert
    fun insertAll(vararg event: Event)

    @Delete
    fun delete(event: Event)
}