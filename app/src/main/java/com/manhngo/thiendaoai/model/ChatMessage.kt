package com.manhngo.thiendaoai.model

enum class MessageType { SYSTEM, USER }

data class ChatMessage(
    val content: String,
    val type: MessageType,
    val timestamp: Long = System.currentTimeMillis()
)
