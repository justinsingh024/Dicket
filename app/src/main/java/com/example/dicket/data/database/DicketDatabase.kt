package com.example.dicket.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dicket.data.converters.Converters
import com.example.dicket.data.dao.CategoryDao
import com.example.dicket.data.dao.EventDao
import com.example.dicket.data.dao.HelpsDao
import com.example.dicket.data.dao.LocationDao
import com.example.dicket.data.dao.OrganizesDao
import com.example.dicket.data.dao.TicketDao
import com.example.dicket.data.dao.UserDao
import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Helps
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.Organizes
import com.example.dicket.data.entity.Ticket
import com.example.dicket.data.entity.User

@Database(
    entities = [User::class, Ticket::class, Organizes::class, Helps::class, Event::class, Location::class, Category::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class DicketDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun ticketDao(): TicketDao
    abstract fun organizesDao(): OrganizesDao
    abstract fun helpsDao(): HelpsDao
    abstract fun eventDao(): EventDao
    abstract fun locationDao(): LocationDao
    abstract fun categoryDao(): CategoryDao
}