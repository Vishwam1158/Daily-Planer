package com.viz.to_do_listapp



object Routes {
    val App = "App"
    val Task = "task"
    val Home = NavPage("Home", "homeScreen") //  R.drawable.home,
//
}

data class NavPage(
    val name: String,
//    val icon: Int,
    val route: String
)