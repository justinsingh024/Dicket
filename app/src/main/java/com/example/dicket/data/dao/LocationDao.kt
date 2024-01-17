package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Location

@Dao
interface LocationDao {
    @Insert
    fun insertLocation(location: Location)

    @Query("SELECT * FROM Location WHERE locationID = :locationId")
    fun getLocationById(locationId: Int): Location?

    @Query("SELECT * from location")
    fun getAllLocations(): List<Location>
}
