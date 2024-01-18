package com.example.dicket.data.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * This object provides Room database type converters for handling conversions
 * between `LocalTime`, `LocalDate` and their String representations.
 */
object Converters {

    // Define date and time formatters using DateTimeFormatter
    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    /**
     * Converts a String to `LocalTime`.
     *
     * @param value The input String to be converted.
     * @return `LocalTime` object if the input is not null, otherwise null.
     */
    @TypeConverter
    @JvmStatic
    fun toLocalTime(value: String?): LocalTime? {
        return value?.let {
            // Parse the input String using the timeFormatter
            return LocalTime.parse(it, timeFormatter)
        }
    }

    /**
     * Converts `LocalTime` to its String representation.
     *
     * @param localTime The input `LocalTime` to be converted.
     * @return String representation of `LocalTime` if the input is not null, otherwise null.
     */
    @TypeConverter
    @JvmStatic
    fun fromLocalTime(localTime: LocalTime?): String? {
        return localTime?.format(timeFormatter)
    }

    /**
     * Converts a String to `LocalDate`.
     *
     * @param value The input String to be converted.
     * @return `LocalDate` object if the input is not null, otherwise null.
     */
    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            // Parse the input String using the dateFormatter
            return LocalDate.parse(it, dateFormatter)
        }
    }

    /**
     * Converts `LocalDate` to its String representation.
     *
     * @param localDate The input `LocalDate` to be converted.
     * @return String representation of `LocalDate` if the input is not null, otherwise null.
     */
    @TypeConverter
    @JvmStatic
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.format(dateFormatter)
    }
}