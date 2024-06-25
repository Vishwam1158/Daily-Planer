package com.viz.to_do_listapp


object Routes {
    val App = "App"
    val Task = "task"
    val Home = NavPage("Home", R.drawable.home, "homeScreen")
    val Calendar = NavPage("Calendar", R.drawable.calendar_filled, "calendarScreen")

    val pages = listOf(Home, Calendar)
}

data class NavPage(
    val name: String,
    val icon: Int,
    val route: String
)