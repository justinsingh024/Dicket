package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a location entity in the Dicket application.
 *
 * @property locationID Unique identifier for the location (auto-generated by Room).
 * @property street The street name associated with the location.
 * @property locationName The name of the location.
 * @property houseNumber The house number associated with the location.
 * @property city The city where the location is situated.
 * @property plz The postal code (PLZ) of the location.
 */
@Entity(
    tableName = "Location"
)
data class Location(
    @PrimaryKey(autoGenerate = true)
    val locationID: Int,
    var street: String,
    var locationName: String,
    var houseNumber: String,
    var city: String,
    var plz: Int
)
