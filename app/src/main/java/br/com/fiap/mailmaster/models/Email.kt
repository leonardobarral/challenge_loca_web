package br.com.fiap.mailmaster.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.fiap.mailmaster.models.enums.PriorityEnum
import java.time.LocalDateTime

@Entity(tableName = "tb_email")
class Email (

    @PrimaryKey(autoGenerate = true)
    val id : Long,

    val assunto: String,

    @ColumnInfo(name = "id_user_remetente")
    val idRemetente : Long,

    val body : String,

    @ColumnInfo(name = "data_envio")
    val dataEnvio : LocalDateTime,

    val prioridade : PriorityEnum = PriorityEnum.NORMAL,

    @ColumnInfo(name = "id_email_resposta")
    val idEmailResposta : Long

)