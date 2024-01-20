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

        @Query("SELECT * FROM event WHERE uid IN (:userIds)")
        fun loadAllByIds(userIds: IntArray): List<Event>

        @Query("SELECT * FROM event WHERE time LIKE :first AND " +
                "event_name LIKE :last LIMIT 1")
        fun findByName(first: String, last: String): Event

        @Insert
        fun insertAll(vararg event: Event)

        @Delete
        fun delete(event: Event)
    }