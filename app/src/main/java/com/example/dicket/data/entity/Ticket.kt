package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Ticket",
    foreignKeys = [
        ForeignKey(
            entity = Event::class,
            parentColumns = ["eventID"],
            childColumns = ["eventID"]
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
    val purchaseDate: String,
    val isCancelled: Boolean,
    val isRedeemed: Boolean,
    val isCheckedIn: Boolean,
    val eventID: Int,
    val userID: Int,
    val scannedByUserID: Int?
)
