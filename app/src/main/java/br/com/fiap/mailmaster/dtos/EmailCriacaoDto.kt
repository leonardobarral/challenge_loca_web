package br.com.fiap.mailmaster.dtos

import androidx.room.Entity
import br.com.fiap.mailmaster.models.enums.PriorityEnum
import java.time.LocalDate

@Entity(tableName = "tb_email")
class EmailCriacaoDto(

    val assunto: String,

    val idRemetente: Long,

    val body: String,

    val dataEnvio: LocalDate,

    val prioridade: PriorityEnum = PriorityEnum.NORMAL,

    val idEmailResposta: Long

)