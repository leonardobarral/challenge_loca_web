package br.com.fiap.mailmaster.services

import android.content.Context
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.models.User

class UserService(context: Context) {

    private val userRepository = UserRepository(context)

    fun insert(user: UserCadastroDto): String {
        val user_check = userRepository.selectByEmail(user.email)
        return if (user_check.isNullOrEmpty()) {
            val newUser = User(
                id = user.id,
                email = user.email,
                name = user.name,
            )
            userRepository.insert(newUser)
            userRepository.selectById(newUser.id).id
        } else {
            ""
        }
    }


    fun selecteById(id: String): UserExibitionDto {
        val user = userRepository.selectById(id = id)
        return UserExibitionDto(user)
    }
}


