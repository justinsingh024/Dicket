package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.dicket.data.entity.Event

@Dao
interface EventDao {
    @Insert
    fun insertEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)

    @Transaction
    @Query("SELECT * FROM Event WHERE eventID = :eventId")
    fun getEventById(eventId: Int): Event?

    @Query("SELECT * FROM Event")
    fun getAllEvents(): List<Event>
}
