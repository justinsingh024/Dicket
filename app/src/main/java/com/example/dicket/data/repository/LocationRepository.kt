package com.example.dicket.data.repository

import com.example.dicket.data.dao.LocationDao
import com.example.dicket.data.entity.Location
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationDao: LocationDao) {
    fun getLocationById(id: Int): Location? {
        return locationDao.getLocationById(id)
    }

    fun getAllLocations(): List<Location> {
        return locationDao.getAllLocations()
    }
}