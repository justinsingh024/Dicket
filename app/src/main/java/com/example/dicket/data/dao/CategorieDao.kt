package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Categorie
@Dao
interface CategorieDao {
    @Insert
    fun insertCategorie(categorie: Categorie)

    @Query("SELECT * FROM Categorie WHERE categorieID = :categorieId")
    fun getCategorieById(categorieId: Int): Categorie?
}
