package com.manhngo.thiendaoai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manhngo.thiendaoai.ui.navigation.AppNavigation
import com.manhngo.thiendaoai.ui.screens.chat.ChatScreen
import com.manhngo.thiendaoai.ui.screens.splash.SplashScreen
import com.manhngo.thiendaoai.ui.theme.ThienDaoAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThienDaoAITheme {
                AppNavigation()
            }
        }
    }
}


