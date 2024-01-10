package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Standort

@Dao
interface StandortDao {
    @Insert
    fun insertStandort(standort: Standort)

    @Query("SELECT * FROM Standort WHERE standortID = :standortId")
    fun getStandortById(standortId: Int): Standort?
}
