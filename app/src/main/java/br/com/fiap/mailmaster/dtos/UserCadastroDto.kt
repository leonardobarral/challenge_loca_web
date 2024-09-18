package br.com.fiap.mailmaster.dtos

import java.util.UUID

class UserCadastroDto(
    val id : String = UUID.randomUUID().toString(),
    val email: String,
    val password1: String,
    val password2: String,
    val name: String
)