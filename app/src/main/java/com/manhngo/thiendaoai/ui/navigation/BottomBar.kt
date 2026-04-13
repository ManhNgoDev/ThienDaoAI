package com.manhngo.thiendaoai.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {
    val items = Constants.bottomNavItems
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffFDFBF7), shape = RoundedCornerShape(20.dp))
            .padding(8.dp)

    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if(isSelected) Color(0xffFFE088) else Color(0xffFDFBF7)
                    )
                    .clickable {
                        navController.navigate(item.route) {
                            popUpTo("chat") {saveState = true}
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.label,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.label,
                    fontSize = 12.sp
                )
            }
        }
    }
}
