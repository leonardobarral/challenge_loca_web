package br.com.fiap.mailmaster.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import br.com.fiap.mailmaster.models.enums.ReceiverTypeEnum
import java.time.LocalDateTime

@Entity(tableName = "tb_receiver")
class EmailReceiverUser(

    @PrimaryKey(autoGenerate = true)
    val id : Long,

    @ColumnInfo(name = "id_email")
    val idEmail : Long,

    @ColumnInfo(name = "id_user_receiver")
    val idReceiver : Long,

    @ColumnInfo(name = "receiver_type")
    val receiverType : ReceiverTypeEnum,

    @ColumnInfo(name = "data_recebimento")
    val dataRecebimento : LocalDateTime,

    @ColumnInfo(name = "status_leitura")
    val statusLeitura : Boolean,

    @ColumnInfo(name = "box_folder")
    val boxSizeEmail : BoxFolderEnum = BoxFolderEnum.BOX

)