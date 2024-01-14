package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userID: Int,
    var prename: String,
    var surname: String,
    var email: String,
    var password: String,
    var isVerified: Boolean,
    var discount: Double,
    var birthdate: String
)
