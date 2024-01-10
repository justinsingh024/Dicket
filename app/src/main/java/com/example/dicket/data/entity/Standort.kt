package com.example.dicket.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Standort",
    foreignKeys = [
        ForeignKey(
            entity = Ort::class,
            parentColumns = ["plz"],
            childColumns = ["plz"]
        )
    ],
    indices = [Index("standortID", unique = true)]
)
data class Standort(
    @PrimaryKey
    val standortID: Int,
    val strasse: String,
    val locationname: String,
    val hausnummer: String,
    val plz: Int
)
