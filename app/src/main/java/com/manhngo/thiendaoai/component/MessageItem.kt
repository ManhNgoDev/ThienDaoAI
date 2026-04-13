package com.manhngo.thiendaoai.component

import androidx.compose.runtime.Composable
import com.manhngo.thiendaoai.model.ChatMessage
import com.manhngo.thiendaoai.model.MessageType

@Composable
fun MessageItem(message: ChatMessage) {
    when (message.type) {
        MessageType.SYSTEM -> SystemMessageItem(message)
        MessageType.USER -> UserMessageItem(message)
    }
}