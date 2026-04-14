package com.manhngo.thiendaoai.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manhngo.thiendaoai.data.local.dao.ChatDao
import com.manhngo.thiendaoai.data.local.dao.UserDao
import com.manhngo.thiendaoai.data.local.entity.LocalChatMessage
import com.manhngo.thiendaoai.data.local.entity.LocalChatSession
import com.manhngo.thiendaoai.data.local.entity.LocalUserStats

@Database(entities = [LocalChatMessage::class, LocalUserStats::class, LocalChatSession::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "thien_dao_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
