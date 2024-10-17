package com.example.babydiarycompose.dao

import android.graphics.Bitmap
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.babydiarycompose.model.DailyDiary


@Dao
interface DailyDiaryDao {

    @Query("SELECT * FROM dailydiary where dailydiary.year_and_month_and_day = :currentData")
    fun getDailyDiary(currentData: String): DailyDiary?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyDiary(vararg dailyDiary: DailyDiary?)

    @Query("UPDATE dailydiary SET comment = :comment  WHERE year_and_month_and_day = :currentData")
    fun updateComment(comment: String, currentData: String)

    @Query("UPDATE dailydiary SET picture = :picture  WHERE year_and_month_and_day = :currentData")
    fun updatePicture(picture: Bitmap, currentData: String)

    fun insertOrUpdate(currentData: String, dailyDiary: DailyDiary?) {
        val itemsFromDB = getDailyDiary(currentData)
        if (itemsFromDB == null) {
            insertDailyDiary(dailyDiary)
        } else if (dailyDiary?.comment != null && dailyDiary.comment.isNotEmpty()) {
            updateComment(dailyDiary.comment, currentData)
        } else if (dailyDiary?.picture != null) {
            updatePicture(dailyDiary.picture, currentData)
        }
    }
}