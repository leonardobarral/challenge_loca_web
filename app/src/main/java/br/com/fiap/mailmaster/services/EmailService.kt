package br.com.fiap.mailmaster.services

import android.content.Context
import br.com.fiap.mailmaster.database.repositories.EmailReceiverUserRepository
import br.com.fiap.mailmaster.database.repositories.EmailRepository
import br.com.fiap.mailmaster.database.repositories.UserRepository
import br.com.fiap.mailmaster.dtos.EmailEReceiverCriacaoDto
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.EmailReceiverUser
import br.com.fiap.mailmaster.models.enums.ReceiverTypeEnum

class EmailService(context: Context) {

    private val emailRepository = EmailRepository(context)
    private val userRepository = UserRepository(context)
    private val emailReceiverUserRepository = EmailReceiverUserRepository(context)

    fun findByIDUserSent(id: Long): List<Email> {
        return emailRepository.findByIDUser(id = id)
    }

    fun findByIDUserReceiver(id: Long, box: String): List<Email> {
        return emailRepository.selectByIdReceiver(id = id, box = box)
    }

    fun updateStatus(emailId: Long, userId: Long): Int {
        return emailReceiverUserRepository.updateStatus(emailId = emailId, userId = userId)
    }

    fun updateBox(emailId: Long, userId: Long, box: String): Int {
        return emailReceiverUserRepository.updateBox(emailId = emailId, userId = userId, box = box)
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

        val destinatariosUnicos = email.destinatarios?.distinct() ?: emptyList()
        for (i in destinatariosUnicos) {
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

        val ccsUnicos = email.ccs?.distinct() ?: emptyList()
        for (i in ccsUnicos) {
            emailReceiverUserRepository.insert(
                EmailReceiverUser(
                    dataRecebimento = email.dataRecebimento,
                    idEmail = newId,
                    idReceiver = userRepository.selectByEmail(i),
                    receiverType = ReceiverTypeEnum.CC.toString(),
                    statusLeitura = false
                )
            )
        }

        val ccosUnicos = email.ccos?.distinct() ?: emptyList()
        for (i in ccosUnicos) {
            emailReceiverUserRepository.insert(
                EmailReceiverUser(
                    dataRecebimento = email.dataRecebimento,
                    idEmail = newId,
                    idReceiver = userRepository.selectByEmail(i),
                    receiverType = ReceiverTypeEnum.CCO.toString(),
                    statusLeitura = false
                )
            )
        }
    }

}
