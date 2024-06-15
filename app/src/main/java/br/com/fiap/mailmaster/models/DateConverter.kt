package br.com.fiap.mailmaster.models

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateConverter {
    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    fun toLocalDateTime(dateString: String?): LocalDateTime? {
        return dateString?.let { LocalDateTime.parse(it, formatter) }
    }
}