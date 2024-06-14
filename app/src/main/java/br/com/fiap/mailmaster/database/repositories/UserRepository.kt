package br.com.fiap.mailmaster.database.repositories

import android.content.Context
import br.com.fiap.mailmaster.database.dao.MailDB
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.User

class UserRepository(context: Context) {

    var db = MailDB.getDatabase(context).UserDao()

    fun insert(user: UserCadastroDto): User{
        return db.insert(user = user)
    }

    fun update(user: User): Int{
        return db.update(user = user)
    }

    fun delete(user: User): Int{
        return db.delete(user = user)
    }

    fun login(user: UserLoginDto): User{
        return db.login(senha=user.senha, email = user.email)
    }

    fun selectId(id:Long): User{
        return db.selectId(id = id)
    }

}