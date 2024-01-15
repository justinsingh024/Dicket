package com.example.dicket.data.repository

import com.example.dicket.data.dao.UserDao
import com.example.dicket.data.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    fun getUserByMail(email: String): User? {
        return userDao.getUserByMail(email)
    }
}