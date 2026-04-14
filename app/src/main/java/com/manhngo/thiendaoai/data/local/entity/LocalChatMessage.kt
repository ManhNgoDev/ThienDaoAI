package com.manhngo.thiendaoai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manhngo.thiendaoai.data.model.MessageType

@Entity(tableName = "chat_messages")
data class LocalChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionId: Long,
    val content: String,
    val type: MessageType,
    val timestamp: Long = System.currentTimeMillis()
)
