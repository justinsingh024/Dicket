package com.example.dicket

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dicket.data.dao.EventDao
import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.data.repository.EventRepository
import com.example.dicket.data.util.DatabaseInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DicketModule {

    @Volatile
    private var INSTANCE: DicketDatabase? = null

    private class DbCallback(
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    Log.i("Database Init", "OnCreate")
                    val initializer = DatabaseInitializer()
                    initializer.insertExampleData(it)
                }
            }
        }
    }

    @Singleton
    @Provides
    fun getRepository(dao: EventDao): EventRepository {
        return EventRepository(dao)
    }

    @Singleton
    @Provides
    fun getDao(database: DicketDatabase): EventDao {
        return database.eventDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ): DicketDatabase {
        return INSTANCE ?: synchronized(this) {
            val scope = CoroutineScope(Dispatchers.IO)
            val instance = Room.databaseBuilder(
                app,
                DicketDatabase::class.java,
                "dicket_database",
            )
                .allowMainThreadQueries()
                .addCallback(DbCallback(scope))
                .build()
                .also { INSTANCE = it}
            instance
        }
    }
}
