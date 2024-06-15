package br.com.fiap.mailmaster.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import br.com.fiap.mailmaster.models.EmailReceiverUser

@Dao
interface EmailReceiverUserDao {

    @Insert
    fun insert(emailReceiverUser: EmailReceiverUser): Long

    @Update
    fun update(emailReceiverUser: EmailReceiverUser): Int

    @Delete
    fun delete(emailReceiverUser: EmailReceiverUser): Int

//    @Query("SELECT * FROM tb_receiver WHERE id = :id")
//    fun selectByIdEmail(id: Long): List<EmailReceiverUser>

}