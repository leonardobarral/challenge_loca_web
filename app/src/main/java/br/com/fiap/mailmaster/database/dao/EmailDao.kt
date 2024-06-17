package br.com.fiap.mailmaster.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.mailmaster.models.Email

@Dao
interface EmailDao {

    @Insert
    fun insert(email: Email): Long

    @Update
    fun update(email: Email): Int

    @Delete
    fun delete(email: Email): Int

//    @Query("SELECT * FROM tb_email WHERE id = :id")
//    fun selectId(id: Long): Email

    @Query("SELECT * FROM tb_email WHERE remetente = :remetente")
    fun findByIDUser(remetente: Long): List<Email>

    @Query("SELECT e.* FROM tb_email e INNER JOIN tb_receiver er ON e.id = er.idEmail WHERE er.idReceiver = :idReceiver")
    fun selectByIdReceiver(idReceiver: Long): List<Email>

}