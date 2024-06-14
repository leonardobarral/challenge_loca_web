package br.com.fiap.mailmaster.dtos

import br.com.fiap.mailmaster.models.User

data class UserExibitionDto(
    val id: Long,
    val email: String,
    val nome: String
) {
    constructor(user: User) : this(
        id = user.id,
        email = user.email,
        nome = user.nome
    )
}


