package br.com.fiap.mailmaster.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.EmailReceiverUser

@Dao
interface EmailReceiverUserDao {

    @Insert
    fun insert(emailReceiverUser: EmailReceiverUser): Long

    @Update
    fun update(emailReceiverUser: EmailReceiverUser): Int

    @Delete
    fun delete(emailReceiverUser: EmailReceiverUser): Int

    @Query("SELECT * FROM tb_receiver WHERE idEmail = :emailId AND idReceiver = :userId ORDER BY data_recebimento ASC LIMIT 1")
    fun findByIdUserIdEmail(emailId: Long, userId: Long): EmailReceiverUser

    @Query("SELECT * FROM tb_receiver WHERE idEmail = :emailId ORDER BY data_recebimento ASC")
    fun findByIdEmail(emailId: Long): List<EmailReceiverUser>

    @Query("UPDATE tb_receiver SET status_leitura = TRUE WHERE idEmail = :emailId AND idReceiver = :userId")
    fun updateStatus(emailId: Long, userId: Long): Int

    @Query("UPDATE tb_receiver SET box_folder = :box WHERE idEmail = :emailId AND idReceiver = :userId")
    fun updateBox(emailId: Long, userId: Long, box: String): Int
}