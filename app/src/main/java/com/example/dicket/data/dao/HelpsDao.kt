package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.dicket.data.entity.Helps

@Dao
interface HelpsDao {
    @Insert
    fun insertHelps(helps: Helps)
}
