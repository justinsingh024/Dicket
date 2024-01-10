package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Ort

@Dao
interface OrtDao {
    @Insert
    fun insertOrt(ort: Ort)

    @Query("SELECT * FROM Ort WHERE plz = :plz")
    fun getOrtByPLZ(plz: Int): Ort?
}
