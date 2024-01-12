package com.example.dicket.data.repository

import com.example.dicket.data.dao.EventDao
import com.example.dicket.data.entity.Event
import javax.inject.Inject

class EventRepository
    @Inject constructor(
        private val eventDao: EventDao
    ){
    fun save(event: Event) = eventDao.insertEvent(event)

    fun delete(event: Event) = eventDao.deleteEvent(event)

    fun getAll() = eventDao.getAllEvents()

    fun getById(id: Int) = eventDao.getEventById(id)
}