package com.example.dicket.service

import com.example.dicket.model.Event

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
                description = "Beschreibung für Event $i",
                minAge = 18,
                entry = "Eintrittskarte erforderlich",
                date = 1, // Datum: Aktuelles Datum + i Tage
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