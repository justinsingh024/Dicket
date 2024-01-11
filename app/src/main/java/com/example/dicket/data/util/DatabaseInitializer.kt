package com.example.dicket.data.util

import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.data.entity.*

class DatabaseInitializer {
    fun insertExampleData(database: DicketDatabase) {
        val ortDao = database.ortDao()
        val categorieDao = database.categorieDao()
        val locationDao = database.locationDao()
        val eventDao = database.eventDao()
        val ticketDao = database.ticketDao()
        val organizesDao = database.organizesDao()
        val userDao = database.userDao()
        val helpsDao = database.helpsDao()

        // Beispiel-Daten für City
        ortDao.insertCity(City(12345, "Stadt"))

        // Beispiel-Daten für Categorie
        categorieDao.insertCategorie(Categorie(1, "Musik"))

        // Beispiel-Daten für Location
        locationDao.insertLocation(Location(1, "Hauptstraße", "Musikhalle", "7b", 12345))

        // Beispiel-Daten für Eventen
        for (i in 1..4) {
            eventDao.insertEvent(
                Event(
                    i,
                    "Event $i",
                    "Beschreibung für Event $i",
                    18,
                    "2024-10-3$i 20:00:00",
                    "2024-10-3$i 18:00:00",
                    1,  // Hier festlegen, welche Location-ID zugeordnet wird
                    "bild_$i.jpg",
                    1,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                    50.0 + i * 10,
                    "2024-10-3$i 18:00:00",
                    1000
                )
            )
        }

        // Beispiel-Daten für User
        userDao.insertUser(User(1, "Max", "Mustermann", "max@example.com", "passwort123", true, 10.0, "1990-01-01"))
        userDao.insertUser(User(2, "Lisa", "Müller", "lisa@example.com", "passwort321", true, 15.0, "1997-05-06"))


        // Beispiel-Daten für Tickets
        for (i in 1..4) {
            val ticket = Ticket("ABC${i}123", "2022-01-0$i", false, false, false, i, 1, 2)
            ticketDao.insertTicket(ticket)
        }

        // Beispiel-Daten für Organizes
        for (i in 1..4) {
            organizesDao.insertOrganizes(Organizes(2, i))
        }

        // Beispiel-Daten für HilftAus
        for (i in 1..4) {
            helpsDao.insertHelps(Helps(1, i))
        }
    }
}
