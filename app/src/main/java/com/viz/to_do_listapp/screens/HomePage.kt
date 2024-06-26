package com.viz.to_do_listapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.viz.to_do_listapp.roomDB.Task
import com.viz.to_do_listapp.viewModel.TaskViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.viz.to_do_listapp.R

@Composable
fun HomePage(viewModel: TaskViewModel, navController: NavController) {
    var taskList by remember { mutableStateOf(listOf<Task>()) }
    viewModel.getTasks().observe(LocalLifecycleOwner.current) { taskList = it }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        LazyColumn {
            items(taskList) { task ->
                Column(
                    Modifier.clickable { viewModel.deleteTask(task) }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.task_title_border),
                            contentDescription = ""
                        )

                        IconButton(
                            onClick = {
                                viewModel.toggleTaskCompletion(task)
                            },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 28.dp, top = 4.dp)
                                .size(64.dp),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.task_round),
                                contentDescription = "Example Image",
                                modifier = Modifier.size(48.dp),
                                tint = Color.Black
                            )
                        }
                        if (task.isComplete) {
                            Image(
                                painter = painterResource(R.drawable.task_tick),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(start = 36.dp)
                                    .size(56.dp)
                            )
                        }

                        Text(
                            text = task.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.align(Alignment.CenterStart).padding(start = 108.dp)
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun TempPreview() {
    Box(contentAlignment = Alignment.Center) {

        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(painter = painterResource(id = R.drawable.task_title_border ), contentDescription = "")

            IconButton(onClick = {  },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding( start = 28.dp, top = 4.dp).size(64.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.task_round),
                    contentDescription = "Example Image",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Black
                )
            }
            Image(
                painter = (painterResource(R.drawable.task_tick)),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart).padding(start = 36.dp)
                    .size(56.dp)
            )



            }
            Text(
                text = "vizzzzzzzzzzzzzzz",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 108.dp)
            )
    }
}