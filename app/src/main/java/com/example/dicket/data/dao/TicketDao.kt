package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Ticket

/**
 * Data Access Object (DAO) interface for performing CRUD operations on the Ticket entity.
 */
@Dao
interface TicketDao {
    /**
     * Inserts a new Ticket entity into the database.
     *
     * @param ticket The Ticket object to be inserted.
     */
    @Insert
    fun insertTicket(ticket: Ticket)

    /**
     * Retrieves a ticket by its QR code from the database.
     *
     * @param qrCode The QR code of the ticket to retrieve.
     * @return The Ticket object if found, or null if not present in the database.
     */
    @Query("SELECT * FROM Ticket WHERE qrCode = :qrCode")
    fun getTicketByQRCode(qrCode: String): Ticket?
}
