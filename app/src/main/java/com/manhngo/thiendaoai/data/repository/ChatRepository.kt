package com.manhngo.thiendaoai.data.repository

import com.manhngo.thiendaoai.data.model.ApiMessage
import com.manhngo.thiendaoai.data.model.ChatMessage
import com.manhngo.thiendaoai.data.model.ChatRequest
import com.manhngo.thiendaoai.data.model.MessageType
import com.manhngo.thiendaoai.data.remote.ApiService

import com.manhngo.thiendaoai.data.local.dao.ChatDao
import com.manhngo.thiendaoai.data.local.entity.LocalChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepository(
    private val apiService: ApiService,
    private val chatDao: ChatDao
) {

    fun getMessages(sessionId: Long): Flow<List<ChatMessage>> {
        return chatDao.getMessagesForSession(sessionId).map { locals ->
            locals.map { ChatMessage(content = it.content, type = it.type) }
        }
    }

    suspend fun saveMessage(sessionId: Long, message: ChatMessage) {
        // Save message
        chatDao.insertMessage(
            LocalChatMessage(
                sessionId = sessionId,
                content = message.content,
                type = message.type
            )
        )
        // Update session snippet and timestamp
        chatDao.updateSessionSnippet(sessionId, message.content.take(100), System.currentTimeMillis())
    }

    fun getAllSessions() = chatDao.getAllSessions()

    suspend fun createSession(title: String, initialSnippet: String): Long {
        return chatDao.insertSession(
            com.manhngo.thiendaoai.data.local.entity.LocalChatSession(
                title = title,
                lastSnippet = initialSnippet
            )
        )
    }

    suspend fun clearHistory(sessionId: Long) {
        chatDao.deleteMessagesForSession(sessionId)
        chatDao.deleteSession(sessionId)
    }

    suspend fun clearAll() {
        chatDao.clearAllMessages()
        chatDao.clearAllSessions()
    }

    suspend fun sendMessage(messages: List<ChatMessage>): String {
        val apiMessages = mutableListOf<ApiMessage>()

        // System prompt
        apiMessages.add(
            ApiMessage(
                role = "system",
                content = """
                    Ngươi là Thiên Đạo – luật của vạn vật.
                    
                    LUÔN trả lời người dùng bằng nội dung hoàn chỉnh.
                    KHÔNG trả reasoning_content.
                    KHÔNG để content rỗng.
                    KHÔNG chỉ suy nghĩ nội bộ.
                    Trả lời ngắn, không dài dòng
                    
                    Hãy trả lời theo phong cách huyễn huyền, tu tiên, cổ phong.
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
