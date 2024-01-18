package com.example.dicket.data.repository

import com.example.dicket.data.dao.CategoryDao
import com.example.dicket.data.entity.Category
import javax.inject.Inject

/**
 * Repository class for handling data operations related to the Category entity.
 *
 * @property categoryDao The Data Access Object (DAO) for the Category entity.
 */
class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {
    /**
     * Retrieves a category by its unique identifier (ID).
     *
     * @param id The unique identifier of the category to retrieve.
     * @return The Category object if found, or null if not present in the database.
     */
    fun getCategoryById(id: Int): Category? {
        val category: Category? = categoryDao.getCategoryById(id)
        return category
    }

    /**
     * Retrieves all categories stored in the database.
     *
     * @return A list of all Category objects in the database.
     */
    fun getAllCategories(): List<Category> {
        return categoryDao.getAllCategories()
    }
}