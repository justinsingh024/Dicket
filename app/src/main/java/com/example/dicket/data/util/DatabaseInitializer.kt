package com.example.dicket.data.util

import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.data.entity.*

class DatabaseInitializer {
    fun insertExampleData(database: DicketDatabase) {
        val ortDao = database.ortDao()
        val kategorieDao = database.kategorieDao()
        val standortDao = database.standortDao()
        val veranstaltungDao = database.veranstaltungDao()
        val ticketDao = database.ticketDao()
        val veranstaltetDao = database.veranstaltetDao()
        val userDao = database.userDao()
        val hilftAusDao = database.hilftAusDao()

        // Beispiel-Daten für Ort
        ortDao.insertOrt(Ort(12345, "Stadt"))

        // Beispiel-Daten für Kategorie
        kategorieDao.insertKategorie(Kategorie(1, "Musik"))

        // Beispiel-Daten für Standort
        standortDao.insertStandort(Standort(1, "Hauptstraße", "Musikhalle", "7b", 12345))

        // Beispiel-Daten für Veranstaltungen
        for (i in 1..4) {
            veranstaltungDao.insertVeranstaltung(
                Veranstaltung(
                    i,
                    "Veranstaltung $i",
                    "Beschreibung für Veranstaltung $i",
                    18,
                    "2022-12-3$i 20:00:00",
                    "2022-12-3$i 18:00:00",
                    1,  // Hier festlegen, welche Standort-ID zugeordnet wird
                    "bild_$i.jpg",
                    1,  // Hier festlegen, welche Kategorie-ID zugeordnet wird
                    50.0 + i * 10,
                    "2022-12-3$i 18:00:00",
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

        // Beispiel-Daten für Veranstaltet
        for (i in 1..4) {
            veranstaltetDao.insertVeranstaltet(Veranstaltet(2, i))
        }

        // Beispiel-Daten für HilftAus
        for (i in 1..4) {
            hilftAusDao.insertHilftAus(HilftAus(1, i))
        }
    }
}
