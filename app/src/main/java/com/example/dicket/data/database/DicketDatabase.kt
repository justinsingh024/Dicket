package com.example.dicket.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dicket.data.converters.Converters
import com.example.dicket.data.dao.CategoryDao
import com.example.dicket.data.dao.EventDao
import com.example.dicket.data.dao.HelpsDao
import com.example.dicket.data.dao.LocationDao
import com.example.dicket.data.dao.TicketDao
import com.example.dicket.data.dao.UserDao
import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Helps
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.Ticket
import com.example.dicket.data.entity.User

/**
 * Room Database class for the Dicket application.
 */
@Database(
    entities = [User::class, Ticket::class, Helps::class, Event::class, Location::class, Category::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class DicketDatabase : RoomDatabase() {

    /**
     * Provides access to the UserDao for performing user-related database operations.
     */
    abstract fun userDao(): UserDao

    /**
     * Provides access to the TicketDao for performing ticket-related database operations.
     */
    abstract fun ticketDao(): TicketDao

    /**
     * Provides access to the HelpsDao for performing helps-related database operations.
     */
    abstract fun helpsDao(): HelpsDao

    /**
     * Provides access to the EventDao for performing event-related database operations.
     */
    abstract fun eventDao(): EventDao

    /**
     * Provides access to the LocationDao for performing location-related database operations.
     */
    abstract fun locationDao(): LocationDao

    /**
     * Provides access to the CategoryDao for performing category-related database operations.
     */
    abstract fun categoryDao(): CategoryDao
}
