package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.Category

/**
 * Data Access Object (DAO) interface for performing CRUD operations on the Category entity.
 */
@Dao
interface CategoryDao {
    /**
     * Inserts a new category into the database.
     *
     * @param category The Category object to be inserted.
     */
    @Insert
    fun insertCategory(category: Category)

    /**
     * Retrieves a category by its unique identifier (ID) from the database.
     *
     * @param categoryId The unique identifier of the category to retrieve.
     * @return The Category object if found, or null if not present in the database.
     */
    @Query("SELECT * FROM Category WHERE categoryID = :categoryId")
    fun getCategoryById(categoryId: Int): Category?

    /**
     * Retrieves all categories stored in the database.
     *
     * @return A list of all Category objects in the database.
     */
    @Query("SELECT * from category")
    fun getAllCategories(): List<Category>
}
