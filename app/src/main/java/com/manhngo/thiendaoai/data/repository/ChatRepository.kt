package com.manhngo.thiendaoai.data.repository

import com.manhngo.thiendaoai.data.model.ApiMessage
import com.manhngo.thiendaoai.data.model.ChatMessage
import com.manhngo.thiendaoai.data.model.ChatRequest
import com.manhngo.thiendaoai.data.model.MessageType
import com.manhngo.thiendaoai.data.remote.ApiService

class ChatRepository(private val apiService: ApiService) {

    suspend fun sendMessage(messages: List<ChatMessage>): String {
        val apiMessages = mutableListOf<ApiMessage>()

        // System prompt
        apiMessages.add(
            ApiMessage(
                role = "system",
                content = """
                    Ngươi là Thiên Đạo – luật của vạn vật.
                    
                    LUÔN trả lời người dùng bằng nội dung hoàn chỉnh.
                    KHÔNG để content rỗng.
                    KHÔNG chỉ suy nghĩ nội bộ.
                    
                    Hãy trả lời theo phong cách huyền huyễn, cổ phong.
                """.trimIndent()
            )
        )

        // Chat history mapping
        messages.forEach { chatMessage ->
            apiMessages.add(
                ApiMessage(
                    role = when (chatMessage.type) {
                        MessageType.USER -> "user"
                        MessageType.SYSTEM -> "assistant"
                    },
                    content = chatMessage.content
                )
            )
        }

        return try {
            val response = apiService.sendMessage(ChatRequest(messages = apiMessages))
            val message = response.choices.firstOrNull()?.message
            
            when {
                !message?.content.isNullOrBlank() -> message?.content!!
                !message?.reasoning_content.isNullOrBlank() -> message?.reasoning_content!!
                else -> "Thiên cơ bất khả lộ..."
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
