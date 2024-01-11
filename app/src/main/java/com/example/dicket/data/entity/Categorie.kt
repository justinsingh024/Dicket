package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categorie")
data class Categorie(
    @PrimaryKey
    val categorieID: Int,
    val name: String
)
