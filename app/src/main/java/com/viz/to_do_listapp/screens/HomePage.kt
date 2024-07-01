package com.viz.to_do_listapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.viz.to_do_listapp.roomDB.Task
import com.viz.to_do_listapp.viewModel.TaskViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viz.to_do_listapp.R
import com.viz.to_do_listapp.roomDB.Category
import com.viz.to_do_listapp.ui.theme.DarkPrimaryTint
import com.viz.to_do_listapp.ui.theme.LightPrimaryTint

@Composable
fun HomePage(viewModel: TaskViewModel, darkTheme: Boolean) { //, navController: NavController
    var taskList by remember { mutableStateOf(listOf<Task>()) }
    viewModel.getTasks().observe(LocalLifecycleOwner.current) { taskList = it }

    var categoryList by remember { mutableStateOf(listOf<Category>()) }
    viewModel.getCategories().observe(LocalLifecycleOwner.current) { categoryList = it }

    //for vertical scroll categories list
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .verticalScroll(rememberScrollState()),
    ) {

//        LazyColumn {
//            items(taskList) { task ->
//                AddTask(
//                    task = task,
//                    categories = categoryList,
////                    onTaskCompletionToggle = { updatedTask ->
////                        viewModel.toggleTaskCompletion(updatedTask)
////                    },
////                    onTaskUpdate = { updatedTask ->
////                        viewModel.upsertTask(updatedTask)
////                    }
//            }
//        }

        LazyRow {
            items(categoryList) {category ->

                Row {
                    Button(
                        modifier = Modifier.padding(4.dp),
                        onClick = { viewModel.deleteCategory(category) },
                        shape = RoundedCornerShape(12),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(3.dp),
                            text =  category.name ,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 94.dp, start = 16.dp, end = 16.dp)
            .background(MaterialTheme.colorScheme.background),
//        verticalArrangement = Arrangement.spacedBy(12.dp)

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
                            painter = painterResource(id = R.drawable.new_task_border),
                            contentDescription = "task border",
                            colorFilter = ColorFilter.tint(if (darkTheme) DarkPrimaryTint else LightPrimaryTint)
                        )

                        IconButton(
                            onClick = {
                                viewModel.toggleTaskCompletion(task)
                            },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 20.dp,)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.task_round),
                                contentDescription = "Example Image",
                                modifier = Modifier.size(32.dp),
                                tint = if (darkTheme) DarkPrimaryTint else LightPrimaryTint
                            )
                        }
                        if (task.isComplete) {
                            Image(
                                painter = painterResource(R.drawable.task_tick),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(if (darkTheme) DarkPrimaryTint else LightPrimaryTint),
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(start = 30.dp, top = 4.dp)
                                    .size(34.dp)
                            )
                        }

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 78.dp),
                            text = task.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None
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
            Image(
                painter = painterResource(id = R.drawable.new_task_border),
                contentDescription = ""
            )

            IconButton(
                onClick = {
//                    viewModel.toggleTaskCompletion(task)
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp,)
//                    .size(64.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.task_round),
                    contentDescription = "Example Image",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Black
                )
            }
//            if (task.isComplete) {
                Image(
                    painter = painterResource(R.drawable.task_tick),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 30.dp, top = 4.dp)
                        .size(34.dp)
                )
//            }

            Text(
                text = "Task Title",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
//                textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 78.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePagePreview() {
    Row {

        Button(
            modifier = Modifier.padding(4.dp),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(12),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
        ) {
            Text(
                modifier = Modifier
                    .padding(3.dp),
                text = " category.name ",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}