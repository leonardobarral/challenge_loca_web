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

    @ColumnInfo(name = "remetente")
    val remetente: Long,

    val body: String,

    @ColumnInfo(name = "data_envio")
    val dataEnvio: LocalDate,

    val prioridade: String = PriorityEnum.NORMAL.toString(),

    @ColumnInfo(name = "id_email_resposta")
    val idEmailResposta: Long?


) {
    override fun toString(): String {
        return "Email(id=$id, assunto='$assunto', remetente=$remetente, body='$body', dataEnvio=$dataEnvio, prioridade='$prioridade', idEmailResposta=$idEmailResposta)"
    }
}