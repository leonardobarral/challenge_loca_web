package br.com.fiap.mailmaster.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.mailmaster.models.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(message: Message): Unit

    @Update
    fun update(message: Message): Int

    @Delete
    fun delete(message: Message): Int

    @Query("SELECT * FROM tb_message WHERE idUser = :idUser and box_folder = :box_folder")
    fun findByBoxFolder(idUser: String, box_folder: String): List<Message>

    @Query("SELECT * FROM tb_message WHERE id = :id")
    fun findById(id: String): Message

}