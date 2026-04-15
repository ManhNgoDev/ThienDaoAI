package com.manhngo.thiendaoai.ui.screens.main

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manhngo.thiendaoai.data.local.AppDatabase
import com.manhngo.thiendaoai.data.repository.UserRepository
import com.manhngo.thiendaoai.ui.navigation.BottomBar
import com.manhngo.thiendaoai.ui.screens.chat.ChatScreen
import com.manhngo.thiendaoai.ui.screens.history.HistoryScreen
import com.manhngo.thiendaoai.ui.screens.profile.ProfileScreen
import com.manhngo.thiendaoai.ui.screens.profile.UserViewModel

@SuppressLint("ContextCastToActivity")
@Composable
fun MainScreen(rootNavController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bottomNavController = rememberNavController()

    val userViewModel: UserViewModel = viewModel(
        viewModelStoreOwner = context as ComponentActivity,
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                val db = AppDatabase.getDatabase(context)
                val repository = UserRepository(db.userDao())
                return UserViewModel(repository) as T
            }
        }
    )

    Scaffold(
        bottomBar = {
            BottomBar(bottomNavController)
        }
    ) { padding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "chat",
            modifier = modifier.padding(bottom = padding.calculateBottomPadding())
        ) {
            composable("chat?sessionId={sessionId}") { backStackEntry ->
                val sessionId = backStackEntry.arguments?.getString("sessionId")?.toLongOrNull()
                ChatScreen(userViewModel = userViewModel, sessionId = sessionId, rootNavController = rootNavController)
            }
            composable("history") {
                HistoryScreen(navController = bottomNavController, rootNavController = rootNavController)
            }
            composable("profile") {
                ProfileScreen(userViewModel = userViewModel, rootNavController = rootNavController)
            }
        }
    }
}
