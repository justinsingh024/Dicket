package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dicket.data.entity.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * from event WHERE eventId = :eventId")
    fun getEvent(eventId: Int): List<Event>

    @Query("SELECT * from event")
    fun getAllEvents(): List<Event>

    @Query("SELECT * from event WHERE organizer = :userId")
    fun getEventsByOrganizer(userId: Int): List<Event>

    @Query(
        "SELECT * FROM event " +
                "JOIN ticket ON event.eventID = ticket.eventID " +
                "WHERE ticket.userID = :userId"
    )
    fun getEventsByUserTickets(userId: Int): List<Event>

    @Query("UPDATE event SET maxQuantityTicket = maxQuantityTicket - 1 WHERE eventID = :eventId")
    fun decrementMaxQuantityTicket(eventId: Int)
}
