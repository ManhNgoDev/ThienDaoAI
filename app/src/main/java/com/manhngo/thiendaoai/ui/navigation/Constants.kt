package com.manhngo.thiendaoai.ui.navigation

import androidx.compose.ui.res.painterResource
import com.manhngo.thiendaoai.R

object Constants {
    val bottomNavItems = listOf(
        BottomNavItem(
            label = "Thiên Đạo",
            icon = R.drawable.yin_yang,
            route = "chat"
        ),
        BottomNavItem(
            label = "Tàng Thư",
            icon = R.drawable.book,
            route = "history"
        ),
        BottomNavItem(
            label = "Tu Vi",
            icon = R.drawable.mana,
            route = "profile"
        )
    )

}
