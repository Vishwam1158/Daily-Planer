package com.viz.to_do_listapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.viz.to_do_listapp.animation.AnimationScreen
import com.viz.to_do_listapp.animation.BottomAppBar
import com.viz.to_do_listapp.screens.Calendar
import com.viz.to_do_listapp.screens.HomePage
import com.viz.to_do_listapp.screens.TopAppBar
import com.viz.to_do_listapp.viewModel.TaskViewModel

@Composable
fun App(viewModel : TaskViewModel, navController: NavController) {
    val selectedRoute = remember { mutableStateOf(Routes.Home.route) }

    Scaffold(
        topBar = {
            TopAppBar()
        },
//        bottomBar = {
//            BottomAppBar(
//                selectedRoute = selectedRoute.value,
//                onChange = { route ->
//                    selectedRoute.value = route
//                }
//            )
//        },
//        content = {
//            Box(modifier = Modifier.padding(it)) {}
//        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            when (selectedRoute.value) {
                Routes.Home.route -> HomePage(viewModel, navController)
                Routes.Calendar.route -> Calendar(navController)

            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp), //24.dp
        contentAlignment = Alignment.BottomCenter
    ) {

            BottomAppBar(
                selectedRoute = selectedRoute.value,
                onChange = { route ->
                    selectedRoute.value = route
                }
            )
            AnimationScreen(navController = navController, viewModel)
    }
}

