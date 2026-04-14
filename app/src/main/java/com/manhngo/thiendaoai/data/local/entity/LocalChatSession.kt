package com.manhngo.thiendaoai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_sessions")
data class LocalChatSession(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val lastSnippet: String,
    val timestamp: Long = System.currentTimeMillis()
)
