package com.manhngo.thiendaoai.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manhngo.thiendaoai.screens.MainScreen
import com.manhngo.thiendaoai.screens.SplashScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                onFinished = {
                    navController.navigate("main") {
                        popUpTo("splash") {inclusive = true}
                    }
                }
            )
        }

        composable("main") {
            MainScreen()
        }
    }
}