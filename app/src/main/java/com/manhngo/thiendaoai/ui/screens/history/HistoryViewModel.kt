package com.manhngo.thiendaoai.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manhngo.thiendaoai.data.local.entity.LocalChatSession
import com.manhngo.thiendaoai.data.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: ChatRepository
) : ViewModel() {

    private val _sessions = MutableStateFlow<List<LocalChatSession>>(emptyList())
    val sessions: StateFlow<List<LocalChatSession>> = _sessions.asStateFlow()

    init {
        loadSessions()
    }

    private fun loadSessions() {
        viewModelScope.launch {
            repository.getAllSessions().collect { sessionList ->
                _sessions.value = sessionList
            }
        }
    }

    fun deleteSession(sessionId: Long) {
        viewModelScope.launch {
            repository.clearHistory(sessionId)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }
}
