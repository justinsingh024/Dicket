package com.example.dicket.data.repository

import com.example.dicket.data.dao.LocationDao
import com.example.dicket.data.entity.Location
import javax.inject.Inject

/**
 * Repository class for handling data operations related to the Location entity.
 *
 * @property locationDao The Data Access Object (DAO) for the Location entity.
 */
class LocationRepository @Inject constructor(private val locationDao: LocationDao) {
    /**
     * Retrieves a location by its unique identifier (ID).
     *
     * @param id The unique identifier of the location to retrieve.
     * @return The Location object if found, or null if not present in the database.
     */
    fun getLocationById(id: Int): Location? {
        return locationDao.getLocationById(id)
    }

    /**
     * Retrieves all locations stored in the database.
     *
     * @return A list of all Location objects in the database.
     */
    fun getAllLocations(): List<Location> {
        return locationDao.getAllLocations()
    }
}
