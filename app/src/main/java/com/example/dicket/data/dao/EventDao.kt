package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dicket.data.entity.Event

@Dao
interface EventDao {
    @Insert
    fun insertEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM Event WHERE eventID = :eventId")
    suspend fun getEventById(eventId: Int): Event?

    @Query("SELECT * FROM Event")
    suspend fun getAllEvents(): List<Event>
}
