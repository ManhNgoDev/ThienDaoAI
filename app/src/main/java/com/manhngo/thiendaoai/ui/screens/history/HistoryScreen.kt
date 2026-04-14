package com.manhngo.thiendaoai.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.manhngo.thiendaoai.data.local.AppDatabase
import com.manhngo.thiendaoai.data.repository.ChatRepository
import com.manhngo.thiendaoai.data.remote.ApiService
import com.manhngo.thiendaoai.ui.component.AppHeader
import com.manhngo.thiendaoai.ui.component.HistoryItem

@Composable
fun HistoryScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val viewModel: HistoryViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                val db = AppDatabase.getDatabase(context)
                val apiService = ApiService.create()
                val repository = ChatRepository(apiService, db.chatDao())
                return HistoryViewModel(repository) as T
            }
        }
    )
    
    val sessions by viewModel.sessions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffaf6eb))
    ) {
        AppHeader()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "Tàng Thư Các",
                fontSize = 42.sp,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF1A1A1A)
            )

            Text(
                text = "Lưu trữ tu hành tâm đắc",
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF666666)
            )

            Spacer(modifier = Modifier.height(30.dp))

            if (sessions.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Chưa có điển tích nào được lưu lại...",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sessions) { session ->
                        HistoryItem(
                            session = session,
                            onClick = {
                                navController.navigate("chat?sessionId=${session.id}")
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}
