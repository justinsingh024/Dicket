package com.example.dicket.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.dicket.data.entity.Organizes

@Dao
interface OrganizesDao {
    @Insert
    fun insertOrganizes(organizes: Organizes)
}
