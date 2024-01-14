package com.example.dicket.service

import com.example.dicket.model.Event
import java.time.LocalDate
import java.time.LocalTime

object MockService {
    private val eventList: List<Event>

    init {
        eventList = generateEvents()
    }

    val allEvents: List<Event>
        get() {
            return eventList
        }

    private fun generateEvents(): List<Event> {
        val eventList = mutableListOf<Event>()
        repeat(40) {i ->
            val event = Event(
                title = "Event $i",
                rating = 5.0f,
                description = "Beschreibung für Event $i",
                minAge = 18,
                entry = LocalTime.of(20,30),
                date = LocalDate.of(2023, 4, 20),  // Set to 24 hours from now
                location = "Veranstaltungsort $i",
                image = "",
                category = "Kategorie $i",
                price = 29.99 + i,
                latestCancelingDate = 2, // Spätestes Stornierungsdatum: Aktuelles Datum + i * 12 Stunden
                maxQuantityTicket = 100 + i
            )
            eventList.add(event)
        }
        return eventList
    }
}