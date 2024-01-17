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
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

enum class SortBy(val text: String) {
    NONE("Hot"),
    NAME("Name"),
    DATE("Date")
}

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val categoryRepository: CategoryRepository,
    private val locationRepository: LocationRepository,
    private val userRepository: UserRepository
) : ViewModel() {


    private val _displayEvents = MutableStateFlow(emptyList<Event>())
    val displayEvents: StateFlow<List<Event>> = _displayEvents

    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _events = MutableStateFlow(displayEvents)

    private var _sortedby = MutableStateFlow(SortBy.NONE)
    val sortedBy = _sortedby.asStateFlow()


    init {
        viewModelScope.launch {
            _displayEvents.emit(eventRepository.getAllEvents())
            uiState.collect { currentState ->
                Log.d("OverviewViewModel", "UI State: $currentState")
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text

    }

    fun onSearchPressed() {
        _displayEvents.value = eventRepository.getAllEvents()
        _sortedby.value = SortBy.NONE
        _displayEvents.value = _displayEvents.value.filter { it.title.contains(searchText.value) }


    }

    fun onSortBy(sortBy: SortBy) {
        _displayEvents.value = eventRepository.getAllEvents()
        _searchText.value = ""
        when (sortBy) {
            SortBy.NONE -> {
                _sortedby.value = SortBy.NONE
                return
            }

            SortBy.NAME -> {
                _sortedby.value = SortBy.NAME
                _displayEvents.value = _displayEvents.value.sortedBy { it.title }
            }

            SortBy.DATE -> {
                _sortedby.value = SortBy.DATE
                _displayEvents.value = _displayEvents.value.sortedBy { it.date }
            }

        }

    }

    fun createEvent(
        title: String,
        description: String,
        price: String,
        minAge: String,
        entry: String,
        date: String,
        location: String,
        image: String,
        category: String,
        latestCancelingDate: String,
        maxQuantityTicket: String,
        organizer: String,
    ) {
        // Definiere das Datumsformat, das dem gegebenen String entspricht
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val eventid = eventRepository.getAllEvents().size + 1
        val event = Event(
            eventID = eventid,
            title = title,
            rating = 4.0f,
            description = description,
            minAge = minAge.toInt(),
            entry = LocalTime.of(entry.toInt(), 0),
            date = LocalDate.parse(date, formatter),
            location = 1,  // Hier festlegen, welche Location-ID zugeordnet wird
            image = "bild_1.jpg",
            category = 1,  // Hier festlegen, welche Categorie-ID zugeordnet wird
            price = price.toDouble(),
            latestCancelingDate = System.currentTimeMillis() - 86400000,
            maxQuantityTicket = maxQuantityTicket.toInt(),
            organizer = organizer.toInt()
        )
        viewModelScope.launch {
            eventRepository.insertEvent(event)
        }
    }

    fun insertCheckIn(event: Event) {
        viewModelScope.launch {
            eventRepository.insertEvent(event)
            _displayEvents.emit(eventRepository.getAllEvents())
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

    fun buyTicket(user: User, event: Event) {
        eventRepository.buyTicket(user, event)
    }

    fun getAllCategories(): List<Category>{
        return categoryRepository.getAllCategories()
    }

    fun getAllLocations(): List<Location>{
        return locationRepository.getAllLocations()
    }
}
