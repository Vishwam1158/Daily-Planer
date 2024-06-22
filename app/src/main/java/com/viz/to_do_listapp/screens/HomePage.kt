package com.viz.to_do_listapp.screens

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.viz.to_do_listapp.roomDB.Task
import com.viz.to_do_listapp.viewModel.TaskViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.viz.to_do_listapp.R


@Composable
fun HomePage( viewModel: TaskViewModel, navController: NavController) {
    var title by remember { mutableStateOf(" ") }
    var description by remember { mutableStateOf(" ") }
    val task = Task(title, description)
    var taskList by remember { mutableStateOf(listOf<Task>()) }
    viewModel.getTasks().observe(LocalLifecycleOwner.current) {
        taskList = it
    }

    Column(
        modifier = Modifier.padding(16.dp),
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
        
        Box(
            modifier = Modifier.background(Color.Gray)
        ) {
//            Image(imageVector = painterResource(id = R.drawable.task_container), contentDescription ="" )
//            Image(imageVector = painterResource(id = R.drawable.task_container), contentDescription = "")
//
//            IconButton(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .size(1080.dp),
//                onClick = { /*TODO*/ }) {
//                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.task_container), contentDescription = "")
//            }
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.x),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(1100.dp, 200.dp)
            )
            Column {
            Text(text = "hjgvh")
            Text(text = "hjgvh")
            Text(text = "hjgvh")
            Text(text = "hjgvh")
            Text(text = "hjgvh")
            Text(text = "hjgvh")
            Text(text = "hjgvh")
        }

        }
    }
}


//@Preview
//@Composable
//private fun HomePagePreview() {
//    HomePage(viewModel = TaskViewModel(), navController = NavController())
//}