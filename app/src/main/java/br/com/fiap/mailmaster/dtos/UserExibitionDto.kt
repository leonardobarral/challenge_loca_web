package br.com.fiap.mailmaster.dtos

import br.com.fiap.mailmaster.models.User

data class UserExibitionDto(
    val email: String,
    val name: String
) {
    constructor(user: User) : this(
        email = user.email,
        name = user.name
    )
}


