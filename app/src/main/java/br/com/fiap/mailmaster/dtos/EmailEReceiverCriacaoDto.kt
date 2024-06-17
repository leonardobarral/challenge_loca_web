package br.com.fiap.mailmaster.dtos

import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.enums.PriorityEnum
import java.time.LocalDate


class EmailEReceiverCriacaoDto(

    val assunto: String,

    val remetente: Long,

    val body: String,

    val dataEnvio: LocalDate,

    val prioridade: PriorityEnum = PriorityEnum.NORMAL,

    val idEmailResposta: Long? = null,

    val destinatarios: List<String>?,

    val ccs: List<String>?,

    val ccos: List<String>?,

    val dataRecebimento: LocalDate? = null,

    val statusLeitura: Boolean = false,

    val boxSizeEmail: BoxFolderEnum = BoxFolderEnum.BOX

)