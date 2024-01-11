package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Event

@Dao
interface EventDao {
    @Insert
    fun insertEvent(event: Event)

    @Query("SELECT * FROM Event WHERE eventID = :eventId")
    fun getEventById(eventId: Int): Event?
}
