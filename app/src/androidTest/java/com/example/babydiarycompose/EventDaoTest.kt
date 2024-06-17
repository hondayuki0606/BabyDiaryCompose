package com.example.babydiarycompose

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.babydiarycompose.dao.EventDao
import com.example.babydiarycompose.database.AppDatabase
import org.junit.Before
import org.junit.Test
import com.example.babydiarycompose.model.Event
import org.junit.After

class EventDaoTest {
    private lateinit var dataBase: AppDatabase
    private lateinit var eventDao: EventDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        eventDao = dataBase.eventDao()
    }

    @After
    fun closeDb() {
        dataBase.close()
    }

    @Test
    fun test_dummy() {
        // prepare
        val event = Event(
            uid = 0,
            yearAndMonthAndDay = "2024/03/12",
            timeStamp = 1,
            time = "5分",
            icon = 0,
            eventName = "母乳",
            eventDetail = "母乳",
        )
        eventDao.insertAll(event)

        // execute
        val eventValue = eventDao.getAll()

        // verify
        assert(eventValue.size == 1)
        assert(eventValue.first().time == "2024/03/12")
        assert(eventValue.first().icon == 1)
        assert(eventValue.first().eventName == "母乳")
        assert(eventValue.first().eventDetail == "5分")
    }
}