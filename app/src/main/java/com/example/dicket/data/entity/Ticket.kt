package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Ticket",
    foreignKeys = [
        ForeignKey(
            entity = Veranstaltung::class,
            parentColumns = ["veranstaltungsID"],
            childColumns = ["veranstaltungsID"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["userID"],
            childColumns = ["userID"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["userID"],
            childColumns = ["scannedByUserID"]
        )
    ],
    primaryKeys = ["qrCode"]
)
data class Ticket(
    val qrCode: String,
    val kaufdatum: String,
    val istStorniert: Boolean,
    val istEingeloest: Boolean,
    val istCheckin: Boolean,
    val veranstaltungsID: Int,
    val userID: Int,
    val scannedByUserID: Int
)
