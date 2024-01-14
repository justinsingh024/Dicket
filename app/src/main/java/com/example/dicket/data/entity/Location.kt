package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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
