package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Ticket

@Dao
interface TicketDao {
    @Insert
    fun insertTicket(ticket: Ticket)

    @Query("SELECT * FROM Ticket WHERE qrCode = :qrCode")
    fun getTicketByQRCode(qrCode: String): Ticket?
}
