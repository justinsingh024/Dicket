package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Event",
    foreignKeys = [
        ForeignKey(entity = Location::class, parentColumns = ["locationID"], childColumns = ["location"]),
        ForeignKey(entity = Categorie::class, parentColumns = ["categorieID"], childColumns = ["categorieID"])
    ]
)
data class Event(
    @PrimaryKey
    val eventID: Int,
    val title: String,
    val description: String,
    val minAge: Int,
    val entry: String,
    val date: String,
    val location: Int,
    val thumbnail: String,
    val categorieID: Int,
    val price: Double,
    val latestCancelingDate: String,
    val maxQuantityTicket: Int
)
