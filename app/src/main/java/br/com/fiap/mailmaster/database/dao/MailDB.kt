package br.com.fiap.mailmaster.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.mailmaster.models.Email
import br.com.fiap.mailmaster.models.EmailReceiverUser
import br.com.fiap.mailmaster.models.User

@Database(
    entities = [
        User::class,
        Email::class,
        EmailReceiverUser::class
    ],
    version = 1
)
abstract class MailDB: RoomDatabase(){
    abstract fun UserDao(): UserDao
    abstract fun EmailDao(): EmailDao
    abstract fun EmailReceiverUserDao(): EmailReceiverUserDao

    companion object{
        private lateinit var instance:MailDB

        fun getDatabase(context: Context):MailDB{
            if(!::instance.isInitialized){
                instance = Room.databaseBuilder(
                    context,
                    MailDB::class.java,
                    "mail_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
