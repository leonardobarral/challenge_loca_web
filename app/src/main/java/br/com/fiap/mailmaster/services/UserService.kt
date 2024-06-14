package br.com.fiap.mailmaster.services

import android.content.Context
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.dtos.UserLoginDto

class UserService(context: Context) {

    private val userRepository = UserRepository(context)

    fun insert(user: UserCadastroDto): UserExibitionDto {
        return UserExibitionDto(userRepository.insert(user = user))
    }

    fun login(user: UserLoginDto): UserExibitionDto {
        return UserExibitionDto(userRepository.login(user = user))
    }
}