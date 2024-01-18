package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Location

/**
 * Data Access Object (DAO) interface for performing CRUD operations on the Location entity.
 */
@Dao
interface LocationDao {
    /**
     * Inserts a new Location entity into the database.
     *
     * @param location The Location object to be inserted.
     */
    @Insert
    fun insertLocation(location: Location)

    /**
     * Retrieves a location by its unique identifier (ID) from the database.
     *
     * @param locationId The unique identifier of the location to retrieve.
     * @return The Location object if found, or null if not present in the database.
     */
    @Query("SELECT * FROM Location WHERE locationID = :locationId")
    fun getLocationById(locationId: Int): Location?

    /**
     * Retrieves all locations stored in the database.
     *
     * @return A list of all Location objects in the database.
     */
    @Query("SELECT * from location")
    fun getAllLocations(): List<Location>
}
