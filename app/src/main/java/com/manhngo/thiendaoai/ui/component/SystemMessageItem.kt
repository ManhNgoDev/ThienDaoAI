package com.manhngo.thiendaoai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manhngo.thiendaoai.data.model.ChatMessage

@Composable
fun SystemMessageItem(message: ChatMessage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 8.dp)
    ) {
        Text(
            text = "Thiên Đạo AI",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff574500),
            modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp)) // ít bo hơn (rectangle style)
                .background(Color(0xfffdfcf9))
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = message.content,
                color = Color.Black
            )
        }
    }
}
