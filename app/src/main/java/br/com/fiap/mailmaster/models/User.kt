package br.com.fiap.mailmaster.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@TypeConverters(DateConverter::class)
@Entity(tableName = "tb_user")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val email: String,
    val senha: String,
    val nome: String

)