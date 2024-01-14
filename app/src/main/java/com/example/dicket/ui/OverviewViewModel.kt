package com.example.dicket.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicket.data.EventUiState
import com.example.dicket.data.repository.EventRepository
import com.example.dicket.data.entity.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val repository: EventRepository
):ViewModel() {

    private val _allEvents = MutableStateFlow(emptyList<Event>())
    val allEvents: StateFlow<List<Event>> = _allEvents

    init {
        viewModelScope.launch {
            _allEvents.emit(repository.getAllEvents())
        }
    }

    fun insertCheckIn(event: Event) {
        viewModelScope.launch {
            repository.insertEvent(event)
            _allEvents.emit(repository.getAllEvents())
        }
    }

    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState.asStateFlow()

    fun login(mail: String, password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoggedIn = true
            )
        }
    }
}