package com.viz.to_do_listapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.viz.to_do_listapp.screens.CustomFloatingActionButton
import com.viz.to_do_listapp.screens.HomePage
import com.viz.to_do_listapp.screens.TopAppBar
import com.viz.to_do_listapp.viewModel.TaskViewModel

@Composable
fun App(
    viewModel: TaskViewModel,
    navController: NavController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    var showSearch by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                darkTheme,
                onThemeUpdated,
                onFilterClick = { filterOption ->
                    viewModel.applyFilter(filterOption)
                },
                onSearchClick = {
                    showSearch = true
                }
            )
        },
        bottomBar = {
            CustomFloatingActionButton(
                onClick = { navController.navigate(Routes.Task) },
                icon = Icons.Filled.Add
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(it)
            ) {
                HomePage(viewModel = viewModel, darkTheme = darkTheme, showSearch = showSearch) {
                    showSearch = false
                }
            }
        }
    )
}
