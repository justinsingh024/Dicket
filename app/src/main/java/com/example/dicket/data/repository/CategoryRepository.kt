package com.example.dicket.data.repository

import com.example.dicket.data.dao.CategoryDao
import com.example.dicket.data.entity.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {
    fun getCategoryById(id: Int): Category? {
        val category: Category? = categoryDao.getCategoryById(id)
        return category
    }

    fun getAllCategories(): List<Category> {
        return categoryDao.getAllCategories()
    }
}