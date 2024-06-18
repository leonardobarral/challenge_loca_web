package br.com.fiap.mailmaster.services

import android.content.Context
import android.util.Log
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.User

class UserService(context: Context) {

    private val userRepository = UserRepository(context)

    fun insert(user: UserCadastroDto):Long {
        Log.d("LEO - SERVICE - INSERT", user.toString())
        val emailId = userRepository.selectByEmail(user.email)

        if(user.senha == user.senha1){
            val idInsert = userRepository.insert(user = User(
                email = user.email,
                nome = user.nome,
                senha = user.senha
            ))

            return idInsert
        }else{
            return 0L
        }

    }

    fun login(user: UserLoginDto): UserExibitionDto? {
        val user = userRepository.login(user = user)
        return user?.let { UserExibitionDto(it) }
    }

    fun selecteById(id: Long): UserExibitionDto? {
        val user = userRepository.selectById(id = id)
        return user?.let { UserExibitionDto(it) }
    }
}


