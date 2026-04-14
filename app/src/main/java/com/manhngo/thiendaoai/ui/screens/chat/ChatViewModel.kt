package com.manhngo.thiendaoai.ui.screens.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manhngo.thiendaoai.data.model.ChatMessage
import com.manhngo.thiendaoai.data.model.MessageType
import com.manhngo.thiendaoai.data.model.UserStats
import com.manhngo.thiendaoai.data.repository.ChatRepository
import com.manhngo.thiendaoai.data.repository.CultivationSystem
import com.manhngo.thiendaoai.ui.screens.profile.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val repository: ChatRepository,
    private val onExpEarned: (Long) -> Unit,
    private val initialSessionId: Long? = null
): ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private var currentSessionId: Long? = initialSessionId

    init {
        viewModelScope.launch {
            currentSessionId?.let { sessionId ->
                repository.getMessages(sessionId).collect { history ->
                    _messages.value = history
                }
            } ?: run {
                // Initialize with welcome message if it's a new session
                val welcome = ChatMessage(
                    content = "Ta là Thiên Đạo là luật trời. Ngươi hôm nay đến với ta chắc là cần tìm kiếm lời giải cho vận mệnh của mình hay muốn lĩnh ngộ đạo pháp?",
                    type = MessageType.SYSTEM
                )
                _messages.value = listOf(welcome)
            }
        }
    }

    var input by mutableStateOf("")
        private set

    var isTyping by mutableStateOf(false)
        private set

    fun updateInput(newInput: String) {
        input = newInput
    }

    fun sendMessage() {
        if (input.isNotBlank() && !isTyping) {
            val userMessage = ChatMessage(content = input, type = MessageType.USER)
            val currentInput = input
            input = ""
            isTyping = true

            viewModelScope.launch {
                try {
                    // Ensure session exists
                    if (currentSessionId == null) {
                        // Title is the first message or a snippet
                        val title = currentInput.take(20) + if (currentInput.length > 20) "..." else ""
                        currentSessionId = repository.createSession(title, currentInput)
                        
                        // Save the welcome message first if it's a brand new session
                        repository.saveMessage(currentSessionId!!, _messages.value.first())
                        
                        // Also need to restart the flow collection for the new session
                        launch {
                            repository.getMessages(currentSessionId!!).collect { history ->
                                _messages.value = history
                            }
                        }
                    }

                    currentSessionId?.let { sessionId ->
                        repository.saveMessage(sessionId, userMessage)
                        val response = repository.sendMessage(_messages.value + userMessage)
                        val systemMessage = ChatMessage(
                            content = response,
                            type = MessageType.SYSTEM
                        )
                        repository.saveMessage(sessionId, systemMessage)
                        
                        val exp = (response.length / 2).coerceIn(10, 50).toLong()
                        onExpEarned(exp)
                    }
                } catch (e: Exception) {
                    _messages.value = _messages.value + ChatMessage(
                        content = "Kết nối thần trí với Thiên Đạo bị gián đoạn...",
                        type = MessageType.SYSTEM
                    )
                } finally {
                    isTyping = false
                }
            }
        }
    }
}
