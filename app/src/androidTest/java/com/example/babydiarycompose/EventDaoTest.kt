package com.example.babydiarycompose

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.babydiarycompose.dao.EventDao
import com.example.babydiarycompose.database.AppDatabase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class EventDaoTest {
    private lateinit var dataBase: AppDatabase
    private lateinit var eventDao: EventDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataBase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        eventDao = dataBase.eventDao()
    }

    @Test
    fun test_dummy() = runTest {
//        assertThat(1,1)
    }
}