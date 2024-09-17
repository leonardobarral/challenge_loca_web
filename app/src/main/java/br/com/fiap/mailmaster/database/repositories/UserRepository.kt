package br.com.fiap.mailmaster.database.repositories

import android.content.Context
import br.com.fiap.mailmaster.database.dao.SqliteDB
import br.com.fiap.mailmaster.dtos.UserLoginDto
import br.com.fiap.mailmaster.models.User

class UserRepository(context: Context) {

    private var db = SqliteDB.getDatabase(context).UserDao()

    fun insert(user: User): Unit {
        return db.insert(user = user)
    }

    fun update(user: User): Int {
        return db.update(user = user)
    }

    fun delete(user: User): Int {
        return db.delete(user = user)
    }


    fun selectById(id: String): User {
        return db.selectById(id = id)
    }

    fun selectByEmail(email: String): String {
        return db.selectByEmail(email = email)
    }

}