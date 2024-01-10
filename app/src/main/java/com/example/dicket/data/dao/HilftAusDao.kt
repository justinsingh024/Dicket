package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.HilftAus

@Dao
interface HilftAusDao {
    @Insert
    fun insertHilftAus(hilftAus: HilftAus)
}
