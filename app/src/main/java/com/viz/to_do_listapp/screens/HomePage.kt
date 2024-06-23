package com.viz.to_do_listapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viz.to_do_listapp.roomDB.Task
import com.viz.to_do_listapp.viewModel.TaskViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController


@Composable
fun HomePage( viewModel: TaskViewModel, navController: NavController) {
    var title by remember { mutableStateOf(" ") }
    var description by remember { mutableStateOf(" ") }
    val task = Task(title, description)
    var taskList by remember { mutableStateOf(listOf<Task>()) }
    viewModel.getTasks().observe(LocalLifecycleOwner.current) { taskList = it }

//    val gradient = Brush.verticalGradient(
//        colors = listOf( Color(0xFF222222),Color(0xFFD7D2C9),)
//    )

    Column(
        modifier = Modifier.padding(16.dp),//.background(gradient),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = {
            viewModel.upsertTask(task)
        }) {
            Text(text = "set data")
        }

        TextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text(text = "Title") }
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            placeholder = { Text(text = "description") }
        )

        LazyColumn {
            items(taskList) { task ->
                Column(
                    Modifier.clickable { viewModel.deleteTask(task) }
                ) {
                    Text(text = "Task: ${task.title}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Description: ${task.description}")
                    Divider(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp))
                }
            }
        }
    }
}

