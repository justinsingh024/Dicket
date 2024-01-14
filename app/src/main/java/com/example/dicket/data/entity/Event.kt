package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(
    tableName = "Event",
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = ["locationID"],
            childColumns = ["location"]
        ),
        ForeignKey(
            entity = Categorie::class,
            parentColumns = ["categorieID"],
            childColumns = ["categorie"]
        )
    ]
)
data class Event(
    @PrimaryKey(autoGenerate = true)
    val eventID: Int,
    var title: String,
    var rating: Float,
    var description: String,
    var minAge: Int,
    var entry: LocalTime,
    var date: LocalDate,
    var location: Int,
    var image: String,
    var categorie: Int,
    var price: Double,
    var latestCancelingDate: Long,
    var maxQuantityTicket: Int
)
