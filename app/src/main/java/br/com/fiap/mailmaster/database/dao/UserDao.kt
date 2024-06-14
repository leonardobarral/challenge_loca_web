package br.com.fiap.mailmaster.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.mailmaster.dtos.UserCadastroDto
import br.com.fiap.mailmaster.models.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: UserCadastroDto): User

    @Update
    fun update(user:User): Int

    @Delete
    fun delete(user:User): Int

    @Query("SELECT * FROM tb_user WHERE id = :id")
    fun selectId(id:Long): User

    @Query("SELECT * FROM tb_user WHERE senha = :senha AND email = :email")
    fun login(senha:String,email:String): User

}