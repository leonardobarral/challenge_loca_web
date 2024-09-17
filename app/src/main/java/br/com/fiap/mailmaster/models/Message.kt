package br.com.fiap.mailmaster.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.enums.PriorityEnum
import br.com.fiap.mailmaster.models.enums.ReceiverTypeEnum
import java.time.LocalDate
import java.util.UUID

@TypeConverters(DateConverter::class)
@Entity(tableName = "tb_message")
class Message(

    @PrimaryKey
    val id: String,

    val idUser: String,

    @ColumnInfo(name = "email_remetente")
    val emailRemente: String,

    @ColumnInfo(name = "nome_remetente")
    val nomeRemetente: String,

    val assunto: String,

    val body: String,

    @ColumnInfo(name = "data_envio")
    val dataEnvio: LocalDate = LocalDate.now(),

    val prioridade: String = PriorityEnum.NORMAL.toString(),

    @ColumnInfo(name = "id_message_response")
    val idMessageResponse: String = "",

    val type: String = ReceiverTypeEnum.DE.toString(),

    @ColumnInfo(name = "data_recebimento")
    val dataRecebimento: LocalDate?,

    @ColumnInfo(name = "status_leitura")
    val statusLeitura: Boolean = false,

    @ColumnInfo(name = "box_folder")
    val boxFolder: String = BoxFolderEnum.SENT.toString(),

    val para : String = "",

    val cc : String = "",

    val cco : String = "",

    val updated_at: LocalDate?
) {
}