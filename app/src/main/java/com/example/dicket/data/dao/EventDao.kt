package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dicket.data.entity.Event

/**
 * Data Access Object (DAO) interface for performing CRUD operations on the Event entity.
 */
@Dao
interface EventDao {
    /**
     * Inserts a new event into the database.
     *
     * @param event The Event object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    /**
     * Updates an existing event in the database.
     *
     * @param event The Event object to be updated.
     */
    @Update
    suspend fun update(event: Event)

    /**
     * Deletes an event from the database.
     *
     * @param event The Event object to be deleted.
     */
    @Delete
    suspend fun delete(event: Event)

    /**
     * Retrieves an event by its unique identifier (ID) from the database.
     *
     * @param eventId The unique identifier of the event to retrieve.
     * @return A list containing the Event object if found, or an empty list if not present in the database.
     */
    @Query("SELECT * from event WHERE eventId = :eventId")
    fun getEvent(eventId: Int): List<Event>

    /**
     * Retrieves all events stored in the database.
     *
     * @return A list of all Event objects in the database.
     */
    @Query("SELECT * from event")
    fun getAllEvents(): List<Event>

    /**
     * Retrieves events organized by a specific user.
     *
     * @param userId The unique identifier of the organizer.
     * @return A list of Event objects organized by the specified user.
     */
    @Query("SELECT * from event WHERE organizer = :userId")
    fun getEventsByOrganizer(userId: Int): List<Event>

    /**
     * Retrieves events associated with tickets purchased by a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Event objects associated with tickets purchased by the specified user.
     */
    @Query(
        "SELECT * FROM event " +
                "JOIN ticket ON event.eventID = ticket.eventID " +
                "WHERE ticket.userID = :userId"
    )
    fun getEventsByUserTickets(userId: Int): List<Event>

    /**
     * Decrements the maximum quantity of tickets available for an event.
     *
     * @param eventId The unique identifier of the event.
     */
    @Query("UPDATE event SET maxQuantityTicket = maxQuantityTicket - 1 WHERE eventID = :eventId")
    fun decrementMaxQuantityTicket(eventId: Int)
}
