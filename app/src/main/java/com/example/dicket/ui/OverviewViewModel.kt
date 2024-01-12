package com.example.dicket.ui

import androidx.lifecycle.ViewModel
import com.example.dicket.data.repository.EventRepository
import com.example.dicket.data.entity.Event
import com.example.dicket.service.MockService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    fun allEvents(): List<Event> = eventRepository.getAll()
}