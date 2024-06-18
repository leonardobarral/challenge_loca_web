package br.com.fiap.mailmaster.database.repositories

import android.content.Context
import br.com.fiap.mailmaster.database.dao.MailDB
import br.com.fiap.mailmaster.models.Email

class EmailRepository(context: Context) {

    private var db = MailDB.getDatabase(context).EmailDao()

    //
    fun insert(email: Email): Long {
        return db.insert(email = email)
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

    fun findByIDUser(id: Long): List<Email> {
        return db.findByIDUser(remetente = id)
    }

    fun selectByIdReceiver(id: Long, box: String): List<Email> {
        return db.selectByIdReceiver(idReceiver = id, box = box)
    }



}