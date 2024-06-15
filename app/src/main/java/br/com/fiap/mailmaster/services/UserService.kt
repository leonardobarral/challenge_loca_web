package br.com.fiap.mailmaster.services

import android.content.Context
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.User

class UserService(context: Context) {

    private val userRepository = UserRepository(context)

    fun insert(user: UserCadastroDto):Long {
        val emailId = userRepository.selectByEmail(user.email)
        return if(user.senha === user.senha1 && emailId == null){
            userRepository.insert(user = User(
                email = user.email,
                nome = user.nome,
                senha = user.senha
            ))
        }else{
            0L
        }
    }

    fun login(user: UserLoginDto): UserExibitionDto {
        return UserExibitionDto(userRepository.login(user = user))
    }
}


