package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Represents a relationship between a user and an event where the user provides help.
 *
 * @property userID The ID of the user providing help (foreign key).
 * @property eventID The ID of the event where the user is providing help (foreign key).
 */
@Entity(
    tableName = "Helps",
    primaryKeys = ["userID", "eventID"],
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userID"], childColumns = ["userID"]),
        ForeignKey(entity = Event::class, parentColumns = ["eventID"], childColumns = ["eventID"])
    ]
)
data class Helps(
    var userID: Int,
    var eventID: Int
)
