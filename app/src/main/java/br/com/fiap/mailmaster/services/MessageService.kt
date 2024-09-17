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


//    fun updateStatus(id_message: String, id_user: String): Int {
//        return messageRepository.updateStatus(id_message = id_message, id_user = id_user)
//    }

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
