package com.example.dicket.ui

import androidx.lifecycle.ViewModel
import com.example.dicket.model.Event
import com.example.dicket.service.MockService

class OverviewViewModel: ViewModel() {
    fun allEvents(): List<Event> {
        return MockService.allEvents
    }
}