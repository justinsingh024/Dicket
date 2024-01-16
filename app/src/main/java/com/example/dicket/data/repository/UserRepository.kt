package com.example.dicket.data.repository

import com.example.dicket.data.dao.UserDao
import com.example.dicket.data.entity.User
import java.security.MessageDigest
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    fun getUserByMail(email: String): User? {
        return userDao.getUserByMail(email)
    }

    fun getUserByMailAndPassword(email: String, password: String): User? {
        val user = userDao.getUserByMail(email) ?: return null
        if (isPasswordCorrect(password, user.password, user.salt)) {
            return user
        }
        return null
    }

    fun hashPassword(password: String, salt: ByteArray): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(salt)
        val hashedPassword = md.digest(password.toByteArray())

        // Convert the byte array to a hexadecimal string
        val hexChars = CharArray(hashedPassword.size * 2)
        for (i in hashedPassword.indices) {
            val v = hashedPassword[i].toInt() and 0xFF
            hexChars[i * 2] = "0123456789ABCDEF"[v shr 4]
            hexChars[i * 2 + 1] = "0123456789ABCDEF"[v and 0x0F]
        }
        return String(hexChars)
    }

    fun isPasswordCorrect(
        enteredPassword: String,
        storedHashedPassword: String,
        salt: ByteArray
    ): Boolean {
        val enteredPasswordHash = hashPassword(enteredPassword, salt)
        return enteredPasswordHash == storedHashedPassword
    }
}