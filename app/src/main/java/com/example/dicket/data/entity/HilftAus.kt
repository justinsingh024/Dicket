package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "HilftAus",
    primaryKeys = ["userID", "veranstaltungsID"],
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userID"], childColumns = ["userID"]),
        ForeignKey(entity = Veranstaltung::class, parentColumns = ["veranstaltungsID"], childColumns = ["veranstaltungsID"])
    ]
)
data class HilftAus(
    val userID: Int,
    val veranstaltungsID: Int
)
