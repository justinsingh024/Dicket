package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Location",
    foreignKeys = [
        ForeignKey(
            entity = City::class,
            parentColumns = ["plz"],
            childColumns = ["plz"]
        )
    ],
    indices = [Index("locationID", unique = true)]
)
data class Location(
    @PrimaryKey
    val locationID: Int,
    val street: String,
    val locationName: String,
    val houseNumber: String,
    val plz: Int
)
