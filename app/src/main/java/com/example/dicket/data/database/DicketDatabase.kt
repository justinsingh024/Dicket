package com.example.dicket.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dicket.data.dao.*
import com.example.dicket.data.entity.*
import com.example.dicket.data.util.DatabaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [User::class, Ticket::class, Veranstaltet::class, HilftAus::class, Veranstaltung::class, Standort::class, Ort::class, Kategorie::class],
    version = 2
)
abstract class DicketDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun ticketDao(): TicketDao
    abstract fun veranstaltetDao(): VeranstaltetDao
    abstract fun hilftAusDao(): HilftAusDao
    abstract fun veranstaltungDao(): VeranstaltungDao
    abstract fun standortDao(): StandortDao
    abstract fun ortDao(): OrtDao
    abstract fun kategorieDao(): KategorieDao


    companion object {

        @Volatile private var INSTANCE: DicketDatabase? = null

        fun getInstance(context: Context): DicketDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                DicketDatabase::class.java, "dicket_database.db")
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            val initializer = DatabaseInitializer()
                            initializer.insertExampleData(getInstance(context))
                            //getInstance(context).userDao().insertUser(User(1, "Max", "Mustermann", "max@example.com", "passwort123", true, 10.0, "1990-01-01"))

                        }
                    }
                })
                .build()
    }
}