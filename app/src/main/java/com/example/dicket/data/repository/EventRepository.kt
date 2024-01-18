package com.example.dicket.data.repository

import com.example.dicket.data.dao.EventDao
import com.example.dicket.data.dao.TicketDao
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Ticket
import com.example.dicket.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Repository class for handling data operations related to the Event and Ticket entities.
 *
 * @property eventDao The Data Access Object (DAO) for the Event entity.
 * @property ticketDao The Data Access Object (DAO) for the Ticket entity.
 */
class EventRepository @Inject constructor(
    private val eventDao: EventDao,
    private val ticketDao: TicketDao
) {
    /**
     * Retrieves all events stored in the database.
     *
     * @return A list of all Event objects in the database.
     */
    fun getAllEvents(): List<Event> {
        return eventDao.getAllEvents()
    }

    /**
     * Inserts an event into the database.
     *
     * @param event The Event object to be inserted.
     */
    suspend fun insertEvent(event: Event) {
        withContext(Dispatchers.IO) {
            eventDao.insert(event)
        }
    }

    /**
     * Retrieves events organized by a specific user (organizer).
     *
     * @param user The User object representing the organizer.
     * @return A list of Event objects organized by the specified user.
     */
    fun getEventsByUserOrganizer(user: User?): List<Event> {
        return if (user != null) {
            eventDao.getEventsByOrganizer(user.userID)
        } else {
            emptyList()
        }
    }

    /**
     * Retrieves events associated with tickets purchased by a specific user.
     *
     * @param user The User object representing the ticket purchaser.
     * @return A list of Event objects associated with tickets purchased by the specified user.
     */
    fun getEventsByUserTickets(user: User?): List<Event> {
        return if (user != null) {
            eventDao.getEventsByUserTickets(user.userID)
        } else {
            emptyList()
        }
    }

    /**
     * Buys a ticket for a user to attend a specific event.
     *
     * @param user The User object purchasing the ticket.
     * @param event The Event object for which the ticket is purchased.
     */
    fun buyTicket(user: User, event: Event) {
        // Generate a unique QR code for the ticket
        val qrCode = user.userID.toString() +
                event.eventID.toString() +
                LocalDateTime.now().toString()

        // Insert the ticket into the database
        ticketDao.insertTicket(
            Ticket(
                qrCode,
                LocalDate.now().toString(),
                isCancelled = false,
                isRedeemed = false,
                isCheckedIn = false,
                event.eventID,
                user.userID,
                scannedByUserID = null
            )
        )

        // Decrement the maximum quantity of tickets available for the event
        eventDao.decrementMaxQuantityTicket(event.eventID)
    }
}