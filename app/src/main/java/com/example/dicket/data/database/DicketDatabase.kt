package com.example.dicket.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dicket.data.converters.Converters
import com.example.dicket.data.dao.*
import com.example.dicket.data.entity.*

@Database(
    entities = [User::class, Ticket::class, Organizes::class, Helps::class, Event::class, Location::class, Categorie::class],
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
    abstract fun categorieDao(): CategorieDao
}