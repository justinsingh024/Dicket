package com.example.dicket.data.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Converters {

    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @TypeConverter
    @JvmStatic
    fun toLocalTime(value: String?): LocalTime? {
        return value?.let {
            return LocalTime.parse(it, timeFormatter)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalTime(localTime: LocalTime?): String? {
        return localTime?.format(timeFormatter)
    }

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            return LocalDate.parse(it, dateFormatter)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.format(dateFormatter)
    }
}