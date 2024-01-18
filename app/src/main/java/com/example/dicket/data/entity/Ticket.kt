package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Represents a ticket entity in the Dicket application.
 *
 * @property qrCode The unique QR code associated with the ticket (primary key).
 * @property purchaseDate The date when the ticket was purchased.
 * @property isCancelled A flag indicating whether the ticket is cancelled.
 * @property isRedeemed A flag indicating whether the ticket is redeemed.
 * @property isCheckedIn A flag indicating whether the ticket is checked in.
 * @property eventID The ID of the associated event (foreign key).
 * @property userID The ID of the user who purchased the ticket (foreign key).
 * @property scannedByUserID The ID of the user who scanned the ticket (foreign key, nullable).
 */
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
