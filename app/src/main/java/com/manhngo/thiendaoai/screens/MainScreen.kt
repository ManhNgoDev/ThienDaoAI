package com.manhngo.thiendaoai.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manhngo.thiendaoai.navigation.BottomBar

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "chat",
            // Xóa phần padding top từ Scaffold để nội dung (Header) có thể tràn lên sát Status Bar
            modifier = modifier.padding(bottom = padding.calculateBottomPadding())
        ) {
            composable("chat") {
                ChatScreen()
            }
            composable("history") {
                HistoryScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
        }
    }
}
