package com.example.dicket.data

import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.User

data class EventUiState(
    val maxPart: Int = 10,
    val isLoggedIn: Boolean = false,
    val clickedEvent: Event? = null,
    val clickedEventCategory: Category? = null,
    val clickedEventLocation: Location? = null,
    val currentUser: User? = null,
    val searchText: String = "",
    val isSearching: Boolean = false,
    var loginFailed: Boolean = false,
)