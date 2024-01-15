package com.example.dicket.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicket.data.EventUiState
import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.User
import com.example.dicket.data.repository.CategoryRepository
import com.example.dicket.data.repository.EventRepository
import com.example.dicket.data.repository.LocationRepository
import com.example.dicket.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val categoryRepository: CategoryRepository,
    private val locationRepository: LocationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _allEvents = MutableStateFlow(emptyList<Event>())
    val allEvents: StateFlow<List<Event>> = _allEvents

    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _allEvents.emit(eventRepository.getAllEvents())
            uiState.collect { currentState ->
                Log.d("OverviewViewModel", "UI State: $currentState")
            }
        }
    }

    fun insertCheckIn(event: Event) {
        viewModelScope.launch {
            eventRepository.insertEvent(event)
            _allEvents.emit(eventRepository.getAllEvents())
        }
    }


    /*
        fun login(mail: String, password: String) {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoggedIn = true
                )
            }
        }
        */

    fun login(mail: String, password: String): Boolean {
        val user: User = userRepository.getUserByMailAndPassword(mail, password) ?: return false
        //_uiState.value = _uiState.value.copy(currentUser = userRepository.getUserByMail(mail))
        _uiState.value = _uiState.value.copy(currentUser = user)
        Log.d("OverviewViewModel", "UserMail: $mail")
        _uiState.value = _uiState.value.copy(isLoggedIn = true)
        return true
    }

    fun logout() {
        _uiState.value = _uiState.value.copy(isLoggedIn = false)
        _uiState.value = _uiState.value.copy(currentUser = null)
    }

    fun setClickedEvent(event: Event) {
        _uiState.value = _uiState.value.copy(clickedEvent = event)

        val category: Category? = categoryRepository.getCategoryById(event.category)
        _uiState.value = _uiState.value.copy(clickedEventCategory = category)

        val location: Location? = locationRepository.getLocationById(event.location)
        _uiState.value = _uiState.value.copy(clickedEventLocation = location)
    }

    fun getEventsByUserOrganizer(user: User?): List<Event> {
        return eventRepository.getEventsByUserOrganizer(user)
    }

    fun getEventsByUserTickets(user: User?): List<Event> {
        return eventRepository.getEventsByUserTickets(user)
    }

    fun getLocationByEvent(event: Event): Location? {
        return locationRepository.getLocationById(event.location)
    }
}
