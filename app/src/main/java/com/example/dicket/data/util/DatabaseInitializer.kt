package com.example.dicket.data.util

import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Helps
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.Ticket
import com.example.dicket.data.entity.User
import java.security.MessageDigest
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
        categoryDao.insertCategory(Category(2, "Homeparty"))
        categoryDao.insertCategory(Category(3, "Technoparty"))


        // Beispiel-Daten für Location
        locationDao.insertLocation(
            Location(
                1,
                "Hauptstraße",
                "Concert-hall",
                "7b",
                "Karlsruhe",
                76131
            )
        )

        locationDao.insertLocation(
            Location(
                2,
                "Langestraße",
                "Art-Gallery",
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
        val saltUser1 = generateRandomSalt()
        userDao.insertUser(
            User(
                1,
                "Max",
                "Mustermann",
                "max@example.com",
                hashPassword("test", saltUser1),
                true,
                10.0,
                "1990-01-01",
                saltUser1
            )
        )

        val saltUser2 = generateRandomSalt()
        userDao.insertUser(
            User(
                2,
                "Lisa",
                "Müller",
                "lisa@example.com",
                hashPassword("test", saltUser2),
                true,
                15.0,
                "1997-05-06",
                saltUser2
            )
        )

        // Beispiel-Daten für Eventen
        eventDao.insert(
            Event(
                1,
                "Nature One",
                4.5f,
                "Nature One - The ultimate techno experience! Get your tickets now for " +
                        "Germany's leading open-air festival at Pydna Rocket Base and dive " +
                        "into an unforgettable world of pulsating beats and electronic energy.",
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
                "Mallorca Party - Celebrate the summer on the island of pure fun! " +
                        "Get your tickets for the hottest event in Mallorca and " +
                        "experience unforgettable party nights with hot music, good vibes, " +
                        "and Mediterranean flair.",
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
                "Halloween Special",
                3.5f,
                "Immerse yourself in the dark world of horror! Secure your tickets now for " +
                        "the ultimate Halloween party, where spooky costumes, " +
                        "captivating beats, and eerie decorations ensure an unforgettable " +
                        "evening. Let yourself be embraced by the darkness and experience a " +
                        "special kind of demolition.",
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
                "Buckle up for a heavenly festival experience! Secure your " +
                        "tickets now for Airbeat One, where breathtaking stages, " +
                        "international top DJs, and a euphoric atmosphere make the air " +
                        "vibrate. Dive into the world of electronic music and experience " +
                        "exciting nights under the open sky. Airbeat One - " +
                        "where the beat breaks through the clouds!",
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
                "Livingroom Session",
                4.0f,
                "Experience the distinctive fusion of beats and atmosphere in the " +
                        "legendary Gotec Club! Get your tickets now and immerse yourself in " +
                        "a night of electronic ecstasy. With top-notch DJs and a unique club " +
                        "setting, the Gotec Techno Night becomes an unforgettable experience " +
                        "for all electronic music lovers. Be there and celebrate in the " +
                        "heart of the techno scene!",
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
                "Village championship",
                4.0f,
                "Celebrate the sense of community and fun at the " +
                        "Village Championships! Get your tickets and be part of this " +
                        "unique event where the local music scene, good vibes, " +
                        "and communal celebration take center stage. Experience " +
                        "unforgettable moments in your village and celebrate the diversity " +
                        "of local talents. Village Championships - Where joy is at home!",
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
                "The perfect fusion of powerful techno beats and captivating " +
                        "Italo pop melodies. Experience an unforgettable evening full of " +
                        "contrasts and musical surprises!",
                14,
                LocalTime.of(18, 0),
                LocalDate.of(2024, 5, 1),
                4,  // Hier festlegen, welche Location-ID zugeordnet wird
                "karl_kinski.png",
                2,  // Hier festlegen, welche Categorie-ID zugeordnet wird
                13.50,
                System.currentTimeMillis() - 86400000,
                150,
                1
            )
        )

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

    fun generateRandomSalt(length: Int = 15): ByteArray {
        val salt = ByteArray(length)
        java.security.SecureRandom().nextBytes(salt)
        println(salt)
        return salt
    }

    fun hashPassword(password: String, salt: ByteArray): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(salt)
        val hashedPassword = md.digest(password.toByteArray())

        // Convert the byte array to a hexadecimal string
        val hexChars = CharArray(hashedPassword.size * 2)
        for (i in hashedPassword.indices) {
            val v = hashedPassword[i].toInt() and 0xFF
            hexChars[i * 2] = "0123456789ABCDEF"[v shr 4]
            hexChars[i * 2 + 1] = "0123456789ABCDEF"[v and 0x0F]
        }
        return String(hexChars)
    }
}
