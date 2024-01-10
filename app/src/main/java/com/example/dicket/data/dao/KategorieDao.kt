package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Kategorie
@Dao
interface KategorieDao {
    @Insert
    fun insertKategorie(kategorie: Kategorie)

    @Query("SELECT * FROM Kategorie WHERE kategorieID = :kategorieId")
    fun getKategorieById(kategorieId: Int): Kategorie?
}
