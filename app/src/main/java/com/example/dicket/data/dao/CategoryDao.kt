package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Category

@Dao
interface CategoryDao {
    @Insert
    fun insertCategory(category: Category)

    @Query("SELECT * FROM Category WHERE categoryID = :categoryId")
    fun getCategoryById(categoryId: Int): Category?

    @Query("SELECT * from category")
    fun getAllCategories(): List<Category>

}
