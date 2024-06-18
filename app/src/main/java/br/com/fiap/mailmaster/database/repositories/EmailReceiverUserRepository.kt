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
    fun update(emailReceiverUser: EmailReceiverUser): Int {
        return db.update(emailReceiverUser = emailReceiverUser)
    }

    fun updateStatus(emailId: Long, userId: Long): Int {
        return db.updateStatus(emailId = emailId, userId = userId)
    }

    fun updateBox(emailId: Long, userId: Long, box: String): Int {
        return db.updateBox(emailId = emailId, userId = userId, box = box)
    }

    fun findByIdUserIdEmail(emailId: Long, userId: Long): EmailReceiverUser {
        return db.findByIdUserIdEmail(emailId = emailId, userId = userId)
    }

    fun findByIdEmail(emailId: Long): List<EmailReceiverUser> {
        return db.findByIdEmail(emailId = emailId)
    }


//
//    fun delete(user: User): Int{
//        return db.delete(user = user)
//    }
//
//    fun login(user: UserLoginDto): User{
//        return db.login(senha=user.senha, email = user.email)
//    }

}