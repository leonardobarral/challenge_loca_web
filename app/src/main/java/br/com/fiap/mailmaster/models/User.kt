package br.com.fiap.mailmaster.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "tb_user")
data class User(

    @PrimaryKey
    val id: String,
    val email: String,
    val name: String,

)