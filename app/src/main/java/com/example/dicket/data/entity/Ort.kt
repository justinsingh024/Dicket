package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ort")
data class Ort(
    @PrimaryKey
    val plz: Int,
    val name: String
)
