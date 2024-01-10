package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Veranstaltung

@Dao
interface VeranstaltungDao {
    @Insert
    fun insertVeranstaltung(veranstaltung: Veranstaltung)

    @Query("SELECT * FROM Veranstaltung WHERE veranstaltungsID = :veranstaltungsId")
    fun getVeranstaltungById(veranstaltungsId: Int): Veranstaltung?
}
