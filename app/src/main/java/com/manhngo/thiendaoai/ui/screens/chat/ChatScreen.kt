package com.manhngo.thiendaoai.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manhngo.thiendaoai.data.model.ChatMessage
import com.manhngo.thiendaoai.data.model.MessageType
import com.manhngo.thiendaoai.data.remote.ApiService
import com.manhngo.thiendaoai.data.repository.ChatRepository
import com.manhngo.thiendaoai.ui.component.AppHeader
import com.manhngo.thiendaoai.ui.component.ChatInputBar
import com.manhngo.thiendaoai.ui.component.MessageItem

@Composable
fun ChatScreen() {
    // Pro-style manual DI for demo purposes
    val apiService = remember { ApiService.create() }
    val repository = remember { ChatRepository(apiService) }
    val viewModel: ChatViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return ChatViewModel(repository) as T
            }
        }
    )

    val messages by viewModel.messages.collectAsState()
    val input = viewModel.input
    val isTyping = viewModel.isTyping
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffaf6eb))
    ) {
        AppHeader()

        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            items(messages) { message ->
                MessageItem(message)
            }
            if (isTyping) {
                item {
                    MessageItem(
                        ChatMessage(
                            content = "Thiên Đạo đang suy nghĩ...",
                            type = MessageType.SYSTEM
                        )
                    )
                }
            }
        }

        ChatInputBar(
            input = input,
            onInputChange = { viewModel.updateInput(it) },
            onSendClick = {
                viewModel.sendMessage()
            }
        )


        LaunchedEffect(messages.size, isTyping) {
            if (messages.isNotEmpty() || isTyping) {
                val lastIndex = if (isTyping) messages.size else messages.size - 1
                if (lastIndex >= 0) {
                    listState.animateScrollToItem(lastIndex)
                }
            }
        }
    }
}
