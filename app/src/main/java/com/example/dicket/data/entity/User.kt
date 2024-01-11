package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey
    val userID: Int,
    val prename: String,
    val surname: String,
    val email: String,
    val password: String,
    val isVerified: Boolean,
    val discount: Double,
    val birthdate: String
)
