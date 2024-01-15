package com.example.dicket.data.util

import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Helps
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.Ticket
import com.example.dicket.data.entity.User
import java.time.LocalDate
import java.time.LocalTime

class DatabaseInitializer {
    suspend fun insertExampleData(database: DicketDatabase) {
        val categoryDao = database.categoryDao()
        val locationDao = database.locationDao()
        val eventDao = database.eventDao()
        val ticketDao = database.ticketDao()
        val userDao = database.userDao()
        val helpsDao = database.helpsDao()

        // Beispiel-Daten für Categorie
        categoryDao.insertCategory(Category(1, "Festival"))
        categoryDao.insertCategory(Category(2, "Dorfparty"))
        categoryDao.insertCategory(Category(3, "Technoparty"))


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

        locationDao.insertLocation(
            Location(
                3,
                "Breitestraße",
                "ZKM",
                "10",
                "Karlsruhe",
                76132
            )
        )

        locationDao.insertLocation(
            Location(
                4,
                "Moltkestraße",
                "B-Bau",
                "17",
                "Karlsruhe",
                76133
            )
        )

        locationDao.insertLocation(
            Location(
                5,
                "Raketenstraße",
                "Raketenbasis Pydna",
                "20",
                "Kastellaun",
                56288
            )
        )

        locationDao.insertLocation(
            Location(
                6,
                "Rüppurrer Straße",
                "Agostea",
                "1",
                "Karlsruhe",
                76137
            )
        )

        locationDao.insertLocation(
            Location(
                7,
                "Gablonzer Straße",
                "Gotec",
                "11",
                "Karlsruhe",
                76185
            )
        )

        locationDao.insertLocation(
            Location(
                8,
                "Flugplatzstraße",
                "Flugplatz",
                "1",
                "Neustadt-Glewe",
                19306
            )
        )


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

        // Beispiel-Daten für Eventen
        eventDao.insert(
            Event(
                1,
                "Nature One",
                4.5f,
                "Nature One - Das ultimative Techno-Erlebnis! Sichere dir jetzt " +
                        "Tickets für Deutschlands führendes Open-Air-Festival auf der " +
                        "Raketenbasis Pydna und tauche ein in eine unvergessliche Welt " +
                        "pulsierender Beats und elektronischer Energie.",
                18,
                LocalTime.of(15, 30),
                LocalDate.of(2024, 8, 1),
                5,  // Hier festlegen, welche Location-ID zugeordnet wird
                "nature_one.jpg",
                1,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                50.0,
                System.currentTimeMillis() - 86400000,
                9000,
                1
            )
        )

        eventDao.insert(
            Event(
                2,
                "Mallorca Party",
                4.5f,
                "Mallorca Party - Feier den Sommer auf der Insel des puren Spaßes! " +
                        "Hol dir deine Tickets für das angesagteste Event auf Mallorca und " +
                        "erlebe unvergessliche Partynächte mit heißer Musik, guter Stimmung " +
                        "und mediterranem Flair.",
                16,
                LocalTime.of(19, 0),
                LocalDate.of(2024, 5, 4),
                1,  // Hier festlegen, welche Location-ID zugeordnet wird
                "mallorca_party.jpg",
                2,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                21.99,
                System.currentTimeMillis() - 86400000,
                1000,
                1
            )
        )

        eventDao.insert(
            Event(
                3,
                "Halloween Abriss",
                3.5f,
                "Tauche ein in die düstere Welt des Schreckens! Sichere dir jetzt " +
                        "Tickets für die ultimative Halloween-Party, wo gruselige Kostüme, " +
                        "mitreißende Beats und schaurige Dekorationen für einen unvergesslichen " +
                        "Abend sorgen. Lass dich von der Dunkelheit umarmen und erlebe einen " +
                        "Abriss der besonderen Art.",
                16,
                LocalTime.of(19, 0),
                LocalDate.of(2024, 10, 31),
                6,  // Hier festlegen, welche Location-ID zugeordnet wird
                "agostea.png",
                2,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                12.50,
                System.currentTimeMillis() - 86400000,
                1000,
                1
            )
        )

        eventDao.insert(
            Event(
                4,
                "Airbeat One",
                4.5f,
                "Schnall dich an für ein himmlisches Festivalerlebnis! Sichere dir " +
                        "jetzt Tickets für die Airbeat One, wo atemberaubende Bühnen, " +
                        "internationale Top-DJs und eine euphorische Atmosphäre die Luft zum " +
                        "Vibrieren bringen. Tauche ein in die Welt der elektronischen Musik " +
                        "und erlebe aufregende Nächte unter freiem Himmel. Airbeat One - " +
                        "wo der Beat die Wolken durchbricht!",
                16,
                LocalTime.of(17, 0),
                LocalDate.of(2024, 7, 10),
                8,  // Hier festlegen, welche Location-ID zugeordnet wird
                "airbeat_one.jpg",
                1,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                45.99,
                System.currentTimeMillis() - 86400000,
                1000,
                1
            )
        )

        eventDao.insert(
            Event(
                5,
                "Wohnzimmer Session",
                4.0f,
                "Erlebe die unverwechselbare Fusion von Beats und Atmosphäre im " +
                        "legendären Gotec Club! Hol dir jetzt deine Tickets und tauche ein in " +
                        "eine Nacht voller elektronischer Ekstase. Mit erstklassigen DJs und " +
                        "einer einzigartigen Clubkulisse wird die Gotec Techno Night zu einem " +
                        "unvergesslichen Erlebnis für alle elektronischen Musikliebhaber. Sei " +
                        "dabei und feiere im Herzstück der Techno-Szene!",
                16,
                LocalTime.of(21, 0),
                LocalDate.of(2024, 1, 22),
                7,  // Hier festlegen, welche Location-ID zugeordnet wird
                "gotec.png",
                3,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                17.50,
                System.currentTimeMillis() - 86400000,
                760,
                1
            )
        )

        eventDao.insert(
            Event(
                6,
                "Dorfmeisterschaft",
                4.0f,
                "Feiere das Gemeinschaftsgefühl und den Spaß bei den " +
                        "Dorfmeisterschaften! Hol dir deine Tickets und sei Teil dieses " +
                        "einzigartigen Events, bei dem die lokale Musikszene, gute Stimmung " +
                        "und gemeinschaftliches Feiern im Mittelpunkt stehen. Erlebe " +
                        "unvergessliche Momente in deinem Dorf und feiere die Vielfalt " +
                        "der örtlichen Talente. Dorfmeisterschaften - Wo die Freude zuhause ist!",
                14,
                LocalTime.of(18, 0),
                LocalDate.of(2024, 5, 1),
                2,  // Hier festlegen, welche Location-ID zugeordnet wird
                "dorfmeisterschaft.jpg",
                2,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                17.50,
                System.currentTimeMillis() - 86400000,
                150,
                1
            )
        )

        eventDao.insert(
            Event(
                7,
                "Techno meets Italo",
                4.0f,
                "Die perfekte Fusion aus kraftvollen Techno-Beats und mitreißenden " +
                        "Italoschlager-Melodien. Erlebe einen unvergesslichen Abend voller " +
                        "Kontraste und musikalischer Überraschungen!",
                14,
                LocalTime.of(18, 0),
                LocalDate.of(2024, 5, 1),
                4,  // Hier festlegen, welche Location-ID zugeordnet wird
                "karl_kinski.jpg",
                2,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                13.50,
                System.currentTimeMillis() - 86400000,
                150,
                1
            )
        )

        /*


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
                            1000,
                            1
                        )
                    )
                }
        */

        // Beispiel-Daten für Tickets
        for (i in 3..4) {
            val ticket = Ticket("ABC${i}123", "2022-01-0$i", false, false, false, i, 1, 2)
            ticketDao.insertTicket(ticket)
        }

        // Beispiel-Daten für Helps
        for (i in 1..4) {
            helpsDao.insertHelps(Helps(1, i))
        }
    }
}
