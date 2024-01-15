package com.example.dicket.data

import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Location

data class EventUiState(
    val maxPart: Int = 10,
    val isLoggedIn: Boolean = false,
    val clickedEvent: Event? = null,
    val clickedEventCategory: Category? = null,
    val clickedEventLocation: Location? = null,
)