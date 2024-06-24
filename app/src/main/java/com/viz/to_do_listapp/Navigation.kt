package com.viz.to_do_listapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viz.to_do_listapp.screens.Calendar
import com.viz.to_do_listapp.screens.HomePage
import com.viz.to_do_listapp.screens.Task
import com.viz.to_do_listapp.viewModel.TaskViewModel

@Composable
fun Navigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.App
    ) {
        composable(Routes.App) {
            App(viewModel,navController = navController)
        }
        composable(Routes.Home.route) {
            HomePage(viewModel, navController = navController)
        }
        composable(Routes.Calendar.route) {
            Calendar(navController = navController)
        }
        composable(Routes.Task) {
            Task(viewModel, navController = navController)
        }

    }
}