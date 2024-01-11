package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.City

@Dao
interface CityDao {
    @Insert
    fun insertCity(city: City)

    @Query("SELECT * FROM City WHERE plz = :plz")
    fun getCityByPLZ(plz: Int): City?
}
