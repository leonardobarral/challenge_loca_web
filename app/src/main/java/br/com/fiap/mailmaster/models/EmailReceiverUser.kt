package br.com.fiap.mailmaster.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.fiap.mailmaster.models.enums.BoxFolderEnum
import java.time.LocalDate

@TypeConverters(DateConverter::class)
@Entity(tableName = "tb_receiver")
class EmailReceiverUser(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val idEmail: Long,

    val idReceiver: Long,

    @ColumnInfo(name = "receiver_type")
    val receiverType: String,

    @ColumnInfo(name = "data_recebimento")
    val dataRecebimento: LocalDate?,

    @ColumnInfo(name = "status_leitura")
    val statusLeitura: Boolean,

    @ColumnInfo(name = "box_folder")
    val boxSizeEmail: String = BoxFolderEnum.BOX.toString()

)