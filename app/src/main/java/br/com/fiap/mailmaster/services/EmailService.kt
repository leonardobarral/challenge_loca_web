package br.com.fiap.mailmaster.services

import android.content.Context
import br.com.fiap.mailmaster.database.repositories.EmailReceiverUserRepository
import br.com.fiap.mailmaster.database.repositories.EmailRepository
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.dtos.EmailEReceiverCriacaoDto
import br.com.fiap.mailmaster.dtos.UserExibitionDto
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.EmailReceiverUser
import br.com.fiap.mailmaster.models.enums.ReceiverTypeEnum

class EmailService(context: Context){

    private val emailRepository = EmailRepository(context)
    private val userRepository = UserRepository(context)
    private val emailReceiverUserRepository = EmailReceiverUserRepository(context)

    fun findByIDUser(user: UserExibitionDto): List<Email> {
        return emailRepository.findByIDUser(user = user)
    }

    fun insert(email: EmailEReceiverCriacaoDto) {
        val newId = emailRepository.insert(
            Email(
                assunto = email.assunto,
                body = email.body,
                dataEnvio = email.dataEnvio,
                remetente = email.remetente,
                idEmailResposta = email.idEmailResposta
            )
        )

        for (i in email.destinatarios) {
            emailReceiverUserRepository.insert(
                EmailReceiverUser(
                    dataRecebimento = email.dataRecebimento,
                    idEmail = newId,
                    idReceiver = userRepository.selectByEmail(i),
                    receiverType = ReceiverTypeEnum.PARA.toString(),
                    statusLeitura = false
                )
            )
        }
    }

}
