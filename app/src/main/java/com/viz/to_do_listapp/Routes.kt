package com.viz.to_do_listapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart

object Routes {
    val App = "App"
    val Home = NavPage("Home", R.drawable.home, "homeScreen")
    val Calendar = NavPage("Calendar", R.drawable.home, "calendarScreen")

    val pages = listOf(Home, Calendar)
}

data class NavPage(
    val name: String,
    val icon: Int,
    val route: String
)