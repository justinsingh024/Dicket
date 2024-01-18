package com.example.dicket.data.repository

import com.example.dicket.data.dao.UserDao
import com.example.dicket.data.entity.User
import java.security.MessageDigest
import javax.inject.Inject

/**
 * Repository class for handling data operations related to the User entity.
 *
 * @property userDao The Data Access Object (DAO) for the User entity.
 */
class UserRepository @Inject constructor(private val userDao: UserDao) {
    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object if found, or null if not present in the database.
     */
    fun getUserByMail(email: String): User? {
        return userDao.getUserByMail(email)
    }

    /**
     * Retrieves a user by their email address and password.
     *
     * @param email The email address of the user.
     * @param password The entered password.
     * @return The User object if the email and password match, or null if not found or incorrect.
     */
    fun getUserByMailAndPassword(email: String, password: String): User? {
        val user = userDao.getUserByMail(email) ?: return null
        if (isPasswordCorrect(password, user.password, user.salt)) {
            return user
        }
        return null
    }

    /**
     * Hashes the given password using SHA-256 and the provided salt.
     *
     * @param password The password to be hashed.
     * @param salt The cryptographic salt.
     * @return The hashed password as a hexadecimal string.
     */
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

    /**
     * Checks if the entered password matches the stored hashed password using the provided salt.
     *
     * @param enteredPassword The entered password.
     * @param storedHashedPassword The stored hashed password.
     * @param salt The cryptographic salt.
     * @return true if the passwords match, false otherwise.
     */
    fun isPasswordCorrect(
        enteredPassword: String,
        storedHashedPassword: String,
        salt: ByteArray
    ): Boolean {
        val enteredPasswordHash = hashPassword(enteredPassword, salt)
        return enteredPasswordHash == storedHashedPassword
    }
}