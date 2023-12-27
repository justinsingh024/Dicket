package com.example.dicket.model

data class Event(
    val title: String,
    val description: String,
    val minAge: Int,
    val entry: String, // Eintrittsinformationen
    val date: Long, // Zeitstempel für das Datum des Events
    val location: String,
    val image: String, // URL oder Pfad zur Event-Bildressource
    val category: String,
    val price: Double,
    val latestCancelingDate: Long, // Zeitstempel für das späteste Stornierungsdatum
    val maxQuantityTicket: Int
)
