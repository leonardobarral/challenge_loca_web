package br.com.fiap.mailmaster.services

import android.content.Context
import androidx.room.Transaction
import br.com.fiap.mailmaster.database.repositories.MessageRepository
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.models.Message

class MessageService(context: Context) {

    private val messageRepository = MessageRepository(context)
    private val userRepository = UserRepository(context)


    fun findByBoxFolder(idUser: String, box_folder: String): List<Message> {
        return messageRepository.findByBoxFolder(idUser = idUser , box_folder = box_folder)
    }

    fun findById(id: String): Message {
        return messageRepository.findById(id = id)
    }
    fun findByAll(id: String): List<Message> {
        return messageRepository.findByAll(id = id)
    }

    fun update(message: Message): Int {
        return messageRepository.update(message = message)
    }

//    fun updateBox(id_message: String, id_user: String, box_folder: String): Int {
//        return recipientRepository.updateBox(
//            id_message = id_message,
//            id_user = id_user,
//            box_folder = box_folder
//        )
//    }


    @Transaction
    fun insertNew(message: Message) {
        return messageRepository.insert(message)
    }

}
