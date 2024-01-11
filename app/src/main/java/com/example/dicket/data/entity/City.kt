package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "City")
data class City(
    @PrimaryKey
    val plz: Int,
    val name: String
)
