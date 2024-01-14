package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Organizes",
    primaryKeys = ["userID", "eventID"],
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userID"], childColumns = ["userID"]),
        ForeignKey(entity = Event::class, parentColumns = ["eventID"], childColumns = ["eventID"])
    ]
)
data class Organizes(
    var userID: Int,
    var eventID: Int
)