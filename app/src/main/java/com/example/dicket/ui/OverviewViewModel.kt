package com.example.dicket.ui

import androidx.lifecycle.ViewModel
import com.example.dicket.data.EventUiState
import com.example.dicket.model.Event
import com.example.dicket.service.MockService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OverviewViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState.asStateFlow()


    fun allEvents(): List<Event> {
        return MockService.allEvents
    }

    fun login(mail: String, password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoggedIn = true
            )
        }
    }
}