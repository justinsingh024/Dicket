package com.example.dicket

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dicket.data.dao.CategoryDao
import com.example.dicket.data.dao.EventDao
import com.example.dicket.data.dao.LocationDao
import com.example.dicket.data.dao.UserDao
import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.data.repository.CategoryRepository
import com.example.dicket.data.repository.EventRepository
import com.example.dicket.data.repository.LocationRepository
import com.example.dicket.data.repository.UserRepository
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
    fun getEventRepository(dao: EventDao): EventRepository {
        return EventRepository(dao)
    }

    @Singleton
    @Provides
    fun getEventDao(database: DicketDatabase): EventDao {
        return database.eventDao()
    }

    @Singleton
    @Provides
    fun getCategoryRepository(dao: CategoryDao): CategoryRepository {
        return CategoryRepository(dao)
    }

    @Singleton
    @Provides
    fun getCategoryDao(database: DicketDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Singleton
    @Provides
    fun getLocationRepository(dao: LocationDao): LocationRepository {
        return LocationRepository(dao)
    }

    @Singleton
    @Provides
    fun getLocationDao(database: DicketDatabase): LocationDao {
        return database.locationDao()
    }

    @Singleton
    @Provides
    fun getUserRepository(dao: UserDao): UserRepository {
        return UserRepository(dao)
    }

    @Singleton
    @Provides
    fun getUserDao(database: DicketDatabase): UserDao {
        return database.userDao()
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
                .also { INSTANCE = it }
            instance
        }
    }
}
