package br.com.fiap.mailmaster.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.mailmaster.models.Message
import br.com.fiap.mailmaster.models.User

@Database(
    entities = [
        User::class,
        Message::class
    ],
    version = 1
)
abstract class SqliteDB : RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun MessageDao(): MessageDao


    companion object {
        private lateinit var instance: SqliteDB

        fun getDatabase(context: Context): SqliteDB {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context,
                    SqliteDB::class.java,
                    "db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
