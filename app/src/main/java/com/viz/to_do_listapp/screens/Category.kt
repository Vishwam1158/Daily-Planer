package com.viz.to_do_listapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.viz.to_do_listapp.Routes
import com.viz.to_do_listapp.roomDB.Category
import com.viz.to_do_listapp.viewModel.TaskViewModel

@Composable
fun AddCategory(viewModel: TaskViewModel, navController: NavController) {
    var categoryName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            label = { Text("Category Name") }
        )
        Button(onClick = {
            if (categoryName.isNotEmpty()) {
                viewModel.upsertCategory(Category(name = categoryName))
                categoryName = ""
                navController.navigate(Routes.App)

            }
        }) {
            Text("Add Category")
        }
    }
}
