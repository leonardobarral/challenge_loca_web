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
    var id: String,

    var idUser: String,

    @ColumnInfo(name = "email_remetente")
    var emailRemente: String,

    @ColumnInfo(name = "nome_remetente")
    var nomeRemetente: String,

    var assunto: String,

    var body: String,

    @ColumnInfo(name = "data_envio")
    var dataEnvio: LocalDate = LocalDate.now(),

    var prioridade: String = PriorityEnum.NORMAL.toString(),

    @ColumnInfo(name = "id_message_response")
    var idMessageResponse: String = "",

    var type: String = ReceiverTypeEnum.DE.toString(),

    @ColumnInfo(name = "data_recebimento")
    var dataRecebimento: LocalDate?,

    @ColumnInfo(name = "status_leitura")
    var statusLeitura: Boolean = false,

    @ColumnInfo(name = "box_folder")
    var boxFolder: String = BoxFolderEnum.SENT.toString(),

    var para : String = "",

    var cc : String = "",

    var cco : String = "",

    var updated_at: LocalDate?
) {

    override fun toString(): String {
        return "Message(id='$id', idUser='$idUser', emailRemente='$emailRemente', nomeRemetente='$nomeRemetente', assunto='$assunto', body='$body', dataEnvio=$dataEnvio, prioridade='$prioridade', idMessageResponse='$idMessageResponse', type='$type', dataRecebimento=$dataRecebimento, statusLeitura=$statusLeitura, boxFolder='$boxFolder', para='$para', cc='$cc', cco='$cco', updated_at=$updated_at)"
    }

}