package br.com.fiap.mailmaster.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmailReceiverUserDao {

    @Insert
    fun insert(emailReceiverUserDao: EmailReceiverUserDao): Long

    @Update
    fun update(emailReceiverUserDao: EmailReceiverUserDao): Int

    @Delete
    fun delete(emailReceiverUserDao: EmailReceiverUserDao): Int

    @Query("SELECT * FROM tb_receiver WHERE id_email = :id")
    fun selectAll(id: Long): List<EmailReceiverUserDao>

}