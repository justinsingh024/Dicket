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

class EventRepository @Inject constructor(
    private val eventDao: EventDao,
    private val ticketDao: TicketDao
) {
    fun getAllEvents(): List<Event> {
        return eventDao.getAllEvents()
    }

    suspend fun insertEvent(event: Event) {
        withContext(Dispatchers.IO) {
            eventDao.insert(event)
        }
    }

    fun getEventsByUserOrganizer(user: User?): List<Event> {
        return if (user != null) {
            eventDao.getEventsByOrganizer(user.userID)
        } else {
            emptyList()
        }
    }

    fun getEventsByUserTickets(user: User?): List<Event> {
        return if (user != null) {
            eventDao.getEventsByUserTickets(user.userID)
        } else {
            emptyList()
        }
    }

    fun buyTicket(user: User, event: Event) {
        val qrCode = user.userID.toString() +
                event.eventID.toString() +
                LocalDateTime.now().toString()

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

        eventDao.decrementMaxQuantityTicket(event.eventID)
    }
}