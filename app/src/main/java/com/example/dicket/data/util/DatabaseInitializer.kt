package com.example.dicket.data.util

import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.data.entity.Categorie
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Helps
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.Organizes
import com.example.dicket.data.entity.Ticket
import com.example.dicket.data.entity.User
import java.time.LocalDate
import java.time.LocalTime

class DatabaseInitializer {
    suspend fun insertExampleData(database: DicketDatabase) {
        val categorieDao = database.categorieDao()
        val locationDao = database.locationDao()
        val eventDao = database.eventDao()
        val ticketDao = database.ticketDao()
        val organizesDao = database.organizesDao()
        val userDao = database.userDao()
        val helpsDao = database.helpsDao()

        // Beispiel-Daten für Categorie
        categorieDao.insertCategorie(Categorie(1, "Musik"))

        // Beispiel-Daten für Location
        locationDao.insertLocation(
            Location(
                1,
                "Hauptstraße",
                "Musikhalle",
                "7b",
                "Karlsruhe",
                76131
            )
        )
        locationDao.insertLocation(
            Location(
                2,
                "Langestraße",
                "Kunsthalle",
                "8",
                "Karlsruhe",
                76135
            )
        )
        locationDao.insertLocation(Location(3, "Breitestraße", "ZKM", "10", "Karlsruhe", 76132))
        locationDao.insertLocation(Location(4, "Moltkestraße", "B-Bau", "17", "Karlsruhe", 76133))

        // Beispiel-Daten für Eventen
        for (i in 1..4) {
            eventDao.insert(
                Event(
                    i,
                    "Event $i",
                    1.5f + i,
                    "Beschreibung für Event $i",
                    18,
                    LocalTime.of(20, 30),
                    LocalDate.of(2024, 4, 3 + i),
                    1,  // Hier festlegen, welche Location-ID zugeordnet wird
                    "bild_$i.jpg",
                    1,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                    50.0 + i * 10,
                    System.currentTimeMillis() - 86400000,
                    1000
                )
            )
        }

        // Beispiel-Daten für User
        userDao.insertUser(
            User(
                1,
                "Max",
                "Mustermann",
                "max@example.com",
                "passwort123",
                true,
                10.0,
                "1990-01-01"
            )
        )
        userDao.insertUser(
            User(
                2,
                "Lisa",
                "Müller",
                "lisa@example.com",
                "passwort321",
                true,
                15.0,
                "1997-05-06"
            )
        )


        // Beispiel-Daten für Tickets
        for (i in 1..4) {
            val ticket = Ticket("ABC${i}123", "2022-01-0$i", false, false, false, i, 1, 2)
            ticketDao.insertTicket(ticket)
        }

        // Beispiel-Daten für Organizes
        for (i in 1..4) {
            organizesDao.insertOrganizes(Organizes(2, i))
        }

        // Beispiel-Daten für Helps
        for (i in 1..4) {
            helpsDao.insertHelps(Helps(1, i))
        }
    }
}
