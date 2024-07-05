package com.viz.to_do_listapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viz.to_do_listapp.roomDB.Category
import com.viz.to_do_listapp.screens.AddCategory
import com.viz.to_do_listapp.screens.AddTask
import com.viz.to_do_listapp.screens.Calendar
import com.viz.to_do_listapp.screens.HomePage
import com.viz.to_do_listapp.viewModel.TaskViewModel

@Composable
fun Navigation(viewModel: TaskViewModel, darkTheme : Boolean, onThemeUpdated : () -> Unit) {
    val navController = rememberNavController()
    var categoryList by remember { mutableStateOf(listOf<Category>()) }
    viewModel.getCategories().observe(LocalLifecycleOwner.current) { categoryList = it }

    NavHost(
        navController = navController,
        startDestination = Routes.App
    ) {
        composable(Routes.App) {
            App(viewModel,navController = navController, darkTheme, onThemeUpdated)
        }
        composable(Routes.Home.route) {
            HomePage(viewModel, darkTheme) //, navController = navController
        }
        composable(Routes.Calendar.route) {
            Calendar() //navController = navController
        }
        composable(Routes.Task) {
            AddTask(viewModel, navController = navController, categoryList)
        }
        composable(Routes.category) {
            AddCategory(viewModel = viewModel, navController)
        }

    }
}