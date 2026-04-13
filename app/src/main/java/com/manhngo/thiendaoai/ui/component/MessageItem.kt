package com.manhngo.thiendaoai.ui.component

import androidx.compose.runtime.Composable
import com.manhngo.thiendaoai.data.model.ChatMessage
import com.manhngo.thiendaoai.data.model.MessageType

@Composable
fun MessageItem(message: ChatMessage) {
    when (message.type) {
        MessageType.SYSTEM -> SystemMessageItem(message)
        MessageType.USER -> UserMessageItem(message)
    }
}
