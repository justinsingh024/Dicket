package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.dicket.data.entity.Helps

/**
 * Data Access Object (DAO) interface for performing CRUD operations on the Helps entity.
 */
@Dao
interface HelpsDao {
    /**
     * Inserts a new Helps entity into the database.
     *
     * @param helps The Helps object to be inserted.
     */
    @Insert
    fun insertHelps(helps: Helps)
}
