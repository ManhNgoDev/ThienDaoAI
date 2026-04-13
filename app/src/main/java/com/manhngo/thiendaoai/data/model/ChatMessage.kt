package com.manhngo.thiendaoai.data.model

enum class MessageType { SYSTEM, USER }

data class ChatMessage(
    val content: String,
    val type: MessageType,
    val timestamp: Long = System.currentTimeMillis()
)
