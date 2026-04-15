package com.manhngo.thiendaoai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manhngo.thiendaoai.R

@Composable
fun ChatInputBar(
    input: String,
    onInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {

        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(start = 8.dp)
                .size(48.dp)
                .background(
                    Color(0xffffffff),
                    CircleShape
                )
                .border(1.dp, color = Color(0xffE9C349), shape = CircleShape),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.scroll),
                contentDescription = "Công Pháp",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier.width(10.dp))

        Surface(
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color(0xffE9C349), RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {

            Box(
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (input.isEmpty()) {
                    Text(
                        text = "Nhập tin nhắn...",
                        style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                    )
                }
                BasicTextField(
                    value = input,
                    onValueChange = onInputChange,
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5
                )
            }
        }

        IconButton(
            onClick = onSendClick,
            enabled = input.isNotBlank(),
            modifier = Modifier
                .padding(start = 8.dp)
                .size(48.dp)
                .background(
                    if (input.isNotBlank()) Color(0xffE9C349) else Color.LightGray,
                    CircleShape
                ),
            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Gửi",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
