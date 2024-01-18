package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dicket.data.entity.User

/**
 * Data Access Object (DAO) interface for performing CRUD operations on the User entity.
 */
@Dao
interface UserDao {
    /**
     * Inserts a new User entity into the database.
     *
     * @param user The User object to be inserted.
     */
    @Insert
    fun insertUser(user: User)

    /**
     * Retrieves a user by its unique identifier (ID) from the database.
     *
     * @param userId The unique identifier of the user to retrieve.
     * @return The User object if found, or null if not present in the database.
     */
    @Query("SELECT * FROM User WHERE userID = :userId")
    fun getUserById(userId: Int): User?

    /**
     * Retrieves all users stored in the database.
     *
     * @return A list of all User objects in the database.
     */
    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    /**
     * Retrieves a user by their email address from the database.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object if found, or null if not present in the database.
     */
    @Query("SELECT * FROM User WHERE email = :email")
    fun getUserByMail(email: String): User?

    /**
     * Retrieves a user by their email address and password from the database.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return The User object if found, or null if not present in the database.
     */
    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    fun getUserByMailAndPassword(email: String, password: String): User?
}
