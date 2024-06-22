package com.viz.to_do_listapp


object Routes {
    val App = "App"
    val Home = NavPage("Home", R.drawable.home_filled, "homeScreen")
    val Calendar = NavPage("Calendar", R.drawable.temp3, "calendarScreen")

    val pages = listOf(Home, Calendar)
}

data class NavPage(
    val name: String,
    val icon: Int,
    val route: String
)