package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey
    val userID: Int,
    val vorname: String,
    val nachname: String,
    val email: String,
    val passwort: String,
    val istVerifiziert: Boolean,
    val nachlass: Double,
    val geburtsdatum: String
)
