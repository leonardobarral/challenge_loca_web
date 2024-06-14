package br.com.fiap.mailmaster.database.repositories

import android.content.Context
import br.com.fiap.mailmaster.database.dao.MailDB
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.models.Email

class EmailRepository(context: Context) {

    var db = MailDB.getDatabase(context).EmailDao()
//
//    fun insert(user: User): Long{
//        return db.insert(user = user)
//    }
//
//    fun update(user: User): Int{
//        return db.update(user = user)
//    }
//
//    fun delete(user: User): Int{
//        return db.delete(user = user)
//    }
//
//    fun login(user: UserLoginDto): User{
//        return db.login(senha=user.senha, email = user.email)
//    }

    fun findByIDUser(user: UserExibitionDto): List<Email>{
        return db.findByIDUser(id = user.id)
    }

}