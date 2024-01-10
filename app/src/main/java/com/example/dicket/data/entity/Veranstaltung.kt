package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Veranstaltung",
    foreignKeys = [
        ForeignKey(entity = Standort::class, parentColumns = ["standortID"], childColumns = ["standort"]),
        ForeignKey(entity = Kategorie::class, parentColumns = ["kategorieID"], childColumns = ["kategorieID"])
    ]
)
data class Veranstaltung(
    @PrimaryKey
    val veranstaltungsID: Int,
    val titel: String,
    val beschreibung: String,
    val mindestalter: Int,
    val einlass: String,
    val datum: String,
    val standort: Int,
    val titelbild: String,
    val kategorieID: Int,
    val preis: Double,
    val spaetStornierzeitpunkt: String,
    val maxTicketanzahl: Int
)
