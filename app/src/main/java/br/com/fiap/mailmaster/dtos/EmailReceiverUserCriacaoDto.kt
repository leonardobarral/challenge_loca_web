package br.com.fiap.mailmaster.dtos

import androidx.room.Entity
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.enums.ReceiverTypeEnum
import java.time.LocalDateTime

@Entity(tableName = "tb_receiver")
class EmailReceiverUserCriacaoDto(

    val idEmail: Long,

    val idReceiver: Long,

    val receiverType: ReceiverTypeEnum,

    val dataRecebimento: LocalDateTime,

    val statusLeitura: Boolean,

    val boxSizeEmail: BoxFolderEnum = BoxFolderEnum.BOX

)