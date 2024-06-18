package br.com.fiap.mailmaster.services

import android.content.Context
import br.com.fiap.mailmaster.database.repositories.EmailReceiverUserRepository
import br.com.fiap.mailmaster.database.repositories.EmailRepository
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.dtos.EmailEReceiverCriacaoDto
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.EmailReceiverUser
import br.com.fiap.mailmaster.models.enums.ReceiverTypeEnum

class EmailReceiverUserService(context: Context) {

    private val emailReceiverUserRepository = EmailReceiverUserRepository(context)

    fun findByIdUserIdEmail(emailId: Long, userId: Long): EmailReceiverUser{
        return emailReceiverUserRepository.findByIdUserIdEmail (emailId = emailId, userId = userId)
    }

    fun findByIdEmail(emailId: Long): List<EmailReceiverUser>{
        return emailReceiverUserRepository.findByIdEmail (emailId = emailId)
    }

}
