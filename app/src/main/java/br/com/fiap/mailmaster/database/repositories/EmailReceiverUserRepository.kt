package br.com.fiap.mailmaster.database.repositories

import android.content.Context
import br.com.fiap.mailmaster.database.dao.MailDB
import br.com.fiap.mailmaster.models.EmailReceiverUser

class EmailReceiverUserRepository(context: Context) {

    private var db = MailDB.getDatabase(context).EmailReceiverUserDao()

    //
    fun insert(emailReceiverUser: EmailReceiverUser): Long {
        return db.insert(emailReceiverUser = emailReceiverUser)
    }
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

//    fun findByIDUser(id : Long ): List<EmailReceiverUser>{
//        return db.selectByIdEmail(id = id)
//    }

}