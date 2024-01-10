package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Veranstaltet

@Dao
interface VeranstaltetDao {
    @Insert
    fun insertVeranstaltet(veranstaltet: Veranstaltet)
}
