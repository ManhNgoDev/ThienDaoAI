package com.manhngo.thiendaoai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manhngo.thiendaoai.component.AppHeader
import com.manhngo.thiendaoai.component.ChatInputBar
import com.manhngo.thiendaoai.component.MessageItem
import com.manhngo.thiendaoai.model.ApiMessage
import com.manhngo.thiendaoai.model.ChatMessage
import com.manhngo.thiendaoai.model.ChatRequest
import com.manhngo.thiendaoai.model.MessageType
import com.manhngo.thiendaoai.service.ApiService
import kotlinx.coroutines.launch

@Composable
fun ChatScreen() {
    var messages by remember {
        mutableStateOf(
            listOf(
                ChatMessage(
                    content = "Ta là Thiên Đạo là luật trời. Ngươi hôm nay đến với ta chắc là cần tìm kiếm lời giải cho vận mệnh của mình hay muốn lĩnh ngộ đạo pháp?",
                    type = MessageType.SYSTEM
                )
            )
        )
    }

    var input by remember { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val apiService = remember { ApiService.create() }

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
            onInputChange = { input = it },
            onSendClick = {
                if (input.isNotBlank() && !isTyping) {
                    val userMessage = ChatMessage(content = input, type = MessageType.USER)
                    messages = messages + userMessage
                    input = ""
                    isTyping = true

                    coroutineScope.launch {
                        try {

                            val apiMessages = mutableListOf<ApiMessage>()

                            // system prompt (ép model phải trả lời)
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

                            // history chat
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

                            val response = apiService.sendMessage(
                                ChatRequest(messages = apiMessages)
                            )

                            val message = response.choices.firstOrNull()?.message


                            val botContent = when {
                                !message?.content.isNullOrBlank() -> message?.content!!
                                !message?.reasoning_content.isNullOrBlank() -> message?.reasoning_content!!
                                else -> "Thiên cơ bất khả lộ..."
                            }

                            messages = messages + ChatMessage(
                                content = botContent,
                                type = MessageType.SYSTEM
                            )

                        } catch (e: Exception) {
                            messages = messages + ChatMessage(
                                content = "Kết nối với Thiên Đạo bị gián đoạn...",
                                type = MessageType.SYSTEM
                            )
                        } finally {
                            isTyping = false
                        }
                    }
                }
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
