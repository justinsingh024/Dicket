package com.example.dicket.model

import java.time.LocalDate
import java.time.LocalTime

data class Event(
    val title: String,
    val rating: Float,
    val description: String,
    val minAge: Int,
    val entry: LocalTime, // Eintrittsinformationen
    val date: LocalDate, // Zeitstempel für das Datum des Events
    val location: String,
    val image: String, // URL oder Pfad zur Event-Bildressource
    val category: String,
    val price: Double,
    val latestCancelingDate: Long, // Zeitstempel für das späteste Stornierungsdatum
    val maxQuantityTicket: Int
)
