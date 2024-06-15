package br.com.fiap.mailmaster.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.fiap.mailmaster.models.enums.PriorityEnum
import java.time.LocalDate

@TypeConverters(DateConverter::class)
@Entity(tableName = "tb_email")
class Email(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val assunto: String,

    @ColumnInfo(name = "id_user_remetente")
    val idRemetente: Long,

    val body: String,

    @ColumnInfo(name = "data_envio")
    val dataEnvio: LocalDate,

    val prioridade: String = PriorityEnum.NORMAL.toString(),

    @ColumnInfo(name = "id_email_resposta")
    val idEmailResposta: Long?

)