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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {

    private val _userStats = MutableStateFlow(UserStats())
    val userStats: StateFlow<UserStats> = _userStats.asStateFlow()

    private val _messages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                content = "Ta là Thiên Đạo là luật trời. Ngươi hôm nay đến với ta chắc là cần tìm kiếm lời giải cho vận mệnh của mình hay muốn lĩnh ngộ đạo pháp?",
                type = MessageType.SYSTEM
            )
        )
    )
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

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
            _messages.value = _messages.value + userMessage
            val currentInput = input
            input = ""
            isTyping = true

            viewModelScope.launch {
                try {
                    val response = repository.sendMessage(_messages.value)
                    _messages.value = _messages.value + ChatMessage(
                        content = response,
                        type = MessageType.SYSTEM
                    )
                    _userStats.value = CultivationSystem.addExp(_userStats.value, 50)
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
