package br.com.fiap.mailmaster.database.repositories

import android.content.Context
import br.com.fiap.mailmaster.database.dao.SqliteDB
import br.com.fiap.mailmaster.models.Message

class MessageRepository(context: Context) {

    private var db = SqliteDB.getDatabase(context).MessageDao()

    //
    fun insert(message: Message): Unit {
        return db.insert(message = message)
    }

    fun update(message: Message): Int{
        return db.update(message = message)
    }

    fun delete(message: Message): Int{
        return db.delete(message = message)
    }

    fun findByBoxFolder(idUser: String , box_folder : String): List<Message> {
        return db.findByBoxFolder(idUser = idUser , box_folder = box_folder)
    }

    fun findById(id:String): Message {
        return db.findById(id = id)
    }

}