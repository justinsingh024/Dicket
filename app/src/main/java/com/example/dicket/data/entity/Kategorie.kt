package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kategorie")
data class Kategorie(
    @PrimaryKey
    val kategorieID: Int,
    val name: String
)
