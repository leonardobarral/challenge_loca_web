package br.com.fiap.mailmaster.models

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateConverter {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it, formatter) }
    }
}