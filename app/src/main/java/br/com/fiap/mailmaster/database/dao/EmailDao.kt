package br.com.fiap.mailmaster.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.mailmaster.dtos.EmailCriacaoDto
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.User

@Dao
interface EmailDao {

    @Insert
    fun insert(email: Email): Long

    @Update
    fun update(email: Email): Int

    @Delete
    fun delete(email: Email): Int

    @Query("SELECT * FROM tb_email WHERE id = :id")
    fun selectId(id:Long): User

    @Query("SELECT * FROM tb_email WHERE id_user_remetente = :id")
    fun findByIDUser(id:Long): List<Email>

}