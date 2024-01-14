package com.example.dicket.data.repository

import com.example.dicket.data.dao.EventDao
import com.example.dicket.data.entity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventDao: EventDao) {
    fun getAllEvents(): List<Event> {
        return eventDao.getAllEvents()
    }

    suspend fun insertEvent(event: Event) {
        withContext(Dispatchers.IO) {
            eventDao.insert(event)
        }
    }
}