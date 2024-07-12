package com.viz.to_do_listapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.viz.to_do_listapp.viewModel.TaskViewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.viz.to_do_listapp.R
import com.viz.to_do_listapp.Model.Category
import com.viz.to_do_listapp.ui.theme.DarkPrimaryTint
import com.viz.to_do_listapp.ui.theme.LightPrimaryTint

//@Composable
//fun HomePage(viewModel: TaskViewModel, darkTheme: Boolean) {
//    val allTasks by viewModel.getTasks().observeAsState(emptyList())
//    val userCategories by viewModel.getCategories().observeAsState(emptyList())
//
//    // Add the default "All Tasks" category
//    val defaultCategory = Category(id = -1, name = "All Tasks",) // Assuming you have an icon resource ID for "All Tasks"
//    val categoryList = listOf(defaultCategory) + userCategories
//
//    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
//    var showDialog by remember { mutableStateOf(false) }
//    var categoryToDelete by remember { mutableStateOf<Category?>(null) }
//
//
//
//    // Fetch tasks for the selected category
//    val taskList = if (selectedCategoryId != null && selectedCategoryId != -1) {
//        viewModel.getTasksByCategory(selectedCategoryId!!).observeAsState(emptyList()).value
//    } else {
//        allTasks
//    }
//
//    Column(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.background)
//            .fillMaxSize()
//    ) {
//        // For vertical scroll categories list
//        LazyRow(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            items(categoryList) { category ->
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Button(
//                        modifier = Modifier.padding(4.dp),
//                        onClick = {
//                            selectedCategoryId = if (category.id == -1) null else category.id
//                        },
//                        shape = RoundedCornerShape(12),
//                        colors = if (selectedCategoryId == category.id || (selectedCategoryId == null && category.id == -1)) {
//                            ButtonDefaults.buttonColors( MaterialTheme.colorScheme.onBackground)
//                        } else {
//                            ButtonDefaults.buttonColors(Color.Transparent)
//                        },
//                        border = BorderStroke(1.dp, if (selectedCategoryId == category.id || (selectedCategoryId == null && category.id == -1)) {
//                            MaterialTheme.colorScheme.background
//                        } else {
//                            MaterialTheme.colorScheme.onBackground
//                        })
//                    ) {
//                        Text(
//                            modifier = Modifier.padding(1.dp),
//                            text = category.name,
//                            color = if (selectedCategoryId == category.id || (selectedCategoryId == null && category.id == -1)) {
//                                MaterialTheme.colorScheme.background
//                            } else {
//                                MaterialTheme.colorScheme.onBackground
//                            },
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.SemiBold,
//                        )
//                    }
//                }
//            }
//        }
//
//        // Show delete button if a user-created category is selected
//        if (selectedCategoryId != null && selectedCategoryId != -1) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 8.dp),
//                horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//                IconButton(
//                    onClick = {
//                        val tasksInCategory = taskList.filter { it.categoryId == selectedCategoryId }
//                        val categoryToDeleteTemp = categoryList.find { it.id == selectedCategoryId }
//                        if (tasksInCategory.isEmpty()) {
//                            // Directly delete the category if it has no tasks
//                            if (categoryToDeleteTemp != null) {
//                                viewModel.deleteCategory(categoryToDeleteTemp)
//                                selectedCategoryId = null // Reset to "All Tasks" if the selected category is deleted
//                            }
//                        } else {
//                            // Show dialog to confirm deletion of category with tasks
//                            categoryToDelete = categoryToDeleteTemp
//                            showDialog = true
//                        }
//                    },
//                    modifier = Modifier.size(width = 90.dp, height = 30.dp)
//                ) {
//                    Row(
////                        modifier = Modifier.size(width = 200.dp),
//                        horizontalArrangement = Arrangement.End,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            text = "Delete",
////                          style = MaterialTheme.typography.h6,
//                            color = MaterialTheme.colorScheme.onBackground
//                        )
//                        Icon(
//                            imageVector = Icons.Filled.Delete, // Replace with your delete icon resource
//                            contentDescription = "Delete Category",
//                            tint = MaterialTheme.colorScheme.onBackground
//                        )
//                        Spacer(modifier = Modifier.heightIn(6.dp))
//                    }
//                }
//            }
//        }
//
//        LazyColumn {
//            items(taskList) { task ->
//                Column(
////                    Modifier.clickable {  }
//                ) {
//                    Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                    ) {
//                        Card(
//                            shape = RoundedCornerShape(12.dp),
////                            colors = CardDefaults.cardColors(Color.Transparent),
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .background(Color.Transparent)
//                                    .clip(RoundedCornerShape(12.dp))
//                                    .padding(4.dp)
//                            ) {
//                                Row(
//                                    modifier = Modifier.fillMaxWidth(),
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    Box(
//                                        contentAlignment = Alignment.TopStart,
//                                    ) {
//                                        IconButton(
//                                            onClick = {
//                                                viewModel.toggleTaskCompletion(task)
//                                            },
//                                            modifier = Modifier
//                                                .padding(start = 4.dp)
//                                        ) {
//                                            Icon(
//                                                painter = painterResource(id = R.drawable.task_round),
//                                                contentDescription = "Example Image",
//                                                modifier = Modifier.size(32.dp),
//                                                tint = if (darkTheme) DarkPrimaryTint else LightPrimaryTint
//                                            )
//                                        }
//                                        if (task.isComplete) {
//                                            Image(
//                                                painter = painterResource(R.drawable.task_tick),
//                                                contentDescription = null,
//                                                colorFilter = ColorFilter.tint(if (darkTheme) DarkPrimaryTint else LightPrimaryTint),
//                                                modifier = Modifier
//                                                    .padding(start = 14.dp, top = 4.dp)
//                                                    .size(34.dp)
//                                            )
//                                        }
//                                    }
//                                    Column(
//                                        modifier = Modifier.fillMaxWidth(0.9f),
//                                        horizontalAlignment = Alignment.Start,
//                                    ) {
//                                        Text(
//                                            text = task.title,
//                                            fontSize = 20.sp,
//                                            fontWeight = FontWeight.SemiBold,
//                                            color = if (task.isComplete) Color.Gray else MaterialTheme.colorScheme.onBackground,
//                                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None
//                                        )
//                                        Text(
//                                            text = task.description,
//                                            fontSize = 14.sp,
//                                            fontWeight = FontWeight.SemiBold,
//                                            color = if (task.isComplete) Color.Gray else MaterialTheme.colorScheme.onBackground,
//                                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None
//                                        )
//                                    }
//                                    IconButton(
//                                        onClick = { viewModel.deleteTask(task)},
//                                        modifier = Modifier.size(width = 90.dp, height = 30.dp)
//                                    ) {
//                                        Icon(
//                                            imageVector = Icons.Filled.Delete, // Replace with your delete icon resource
//                                            contentDescription = "Delete Category",
//                                            tint = MaterialTheme.colorScheme.onBackground
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        // Dialog to confirm deletion of category with tasks
//        if (showDialog && categoryToDelete != null) {
//            AlertDialog(
//                onDismissRequest = { showDialog = false },
//                title = { Text("Delete Category") },
//                text = { Text("This category contain tasks. Are you sure you want to delete this category along with all its tasks?") },
//                confirmButton = {
//                    Row (modifier = Modifier
//                        .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween) {
//                        Button(
//                            onClick = {
//                                // Delete category along with all its tasks
//                            val tasksInCategory = taskList.filter { it.categoryId == categoryToDelete!!.id }
//                            tasksInCategory.forEach { viewModel.deleteTask(it) }
//                            viewModel.deleteCategory(categoryToDelete!!)
//                            selectedCategoryId = null // Reset to "All Tasks"
//                            showDialog = false
//                            },
//                            shape = RoundedCornerShape(12),
//                            colors = ButtonDefaults.buttonColors(Color.Transparent),
//                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                        ) {
//                            Text(text = "Yes", color = MaterialTheme.colorScheme.onBackground)
//                        }
//                        Button(
//                            onClick = { showDialog = false },
//                            shape = RoundedCornerShape(12),
//                            colors = ButtonDefaults.buttonColors(Color.Transparent),
//                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                        ) {
//                            Text(text = "No", color = MaterialTheme.colorScheme.onBackground)
//                        }
//
//                    }
//                }
//            )
//        }
//    }
//}

@Composable
fun HomePage(viewModel: TaskViewModel, darkTheme: Boolean, showSearch: Boolean, onSearchDismiss: () -> Unit) {
    val allTasks by viewModel.getTasks().observeAsState(emptyList())
    val userCategories by viewModel.getCategories().observeAsState(emptyList())
    val filterOption by viewModel.filterOption.observeAsState(FilterOption.Alphabet)
    var searchQuery by remember { mutableStateOf("") }

    // Add the default "All Tasks" category
    val defaultCategory = Category(id = -1, name = "All Tasks")
    val categoryList = listOf(defaultCategory) + userCategories

    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var categoryToDelete by remember { mutableStateOf<Category?>(null) }

    // Fetch tasks for the selected category
    val taskList = if (selectedCategoryId != null && selectedCategoryId != -1) {
        viewModel.getTasksByCategory(selectedCategoryId!!).observeAsState(emptyList()).value
    } else {
        allTasks
    }

    // Apply filtering
    val filteredTasks = when (filterOption) {
        FilterOption.Priority -> taskList.sortedBy { it.priority }
        FilterOption.Completed -> taskList.filter { it.isComplete }
        FilterOption.Incompleted -> taskList.filter { !it.isComplete }
        FilterOption.Alphabet -> taskList.sortedBy { it.title }
    }

    // Apply search
    val searchedTasks = if (searchQuery.isNotEmpty()) {
        filteredTasks.filter { it.title.contains(searchQuery, ignoreCase = true) }
    } else {
        filteredTasks
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        if (showSearch) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search tasks...") },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = { onSearchDismiss() }
                )
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categoryList) { category ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        modifier = Modifier.padding(4.dp),
                        onClick = {
                            selectedCategoryId = if (category.id == -1) null else category.id
                        },
                        shape = RoundedCornerShape(12),
                        colors = if (selectedCategoryId == category.id || (selectedCategoryId == null && category.id == -1)) {
                            ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
                        } else {
                            ButtonDefaults.buttonColors(Color.Transparent)
                        },
                        border = BorderStroke(
                            1.dp,
                            if (selectedCategoryId == category.id || (selectedCategoryId == null && category.id == -1)) {
                                MaterialTheme.colorScheme.background
                            } else {
                                MaterialTheme.colorScheme.onBackground
                            }
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(1.dp),
                            text = category.name,
                            color = if (selectedCategoryId == category.id || (selectedCategoryId == null && category.id == -1)) {
                                MaterialTheme.colorScheme.background
                            } else {
                                MaterialTheme.colorScheme.onBackground
                            },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }

        // Show delete button if a user-created category is selected
        if (selectedCategoryId != null && selectedCategoryId != -1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        val tasksInCategory = searchedTasks.filter { it.categoryId == selectedCategoryId }
                        val categoryToDeleteTemp = categoryList.find { it.id == selectedCategoryId }
                        if (tasksInCategory.isEmpty()) {
                            if (categoryToDeleteTemp != null) {
                                viewModel.deleteCategory(categoryToDeleteTemp)
                                selectedCategoryId = null
                            }
                        } else {
                            categoryToDelete = categoryToDeleteTemp
                            showDialog = true
                        }
                    },
                    modifier = Modifier.size(width = 90.dp, height = 30.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Delete",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete Category",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.heightIn(6.dp))
                    }
                }
            }
        }

        LazyColumn {
            items(searchedTasks) { task ->
                Column {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent)
                                    .clip(RoundedCornerShape(12.dp))
                                    .padding(4.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(
                                        contentAlignment = Alignment.TopStart,
                                    ) {
                                        IconButton(
                                            onClick = {
                                                viewModel.toggleTaskCompletion(task)
                                            },
                                            modifier = Modifier.padding(start = 4.dp)
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
                                                    .padding(start = 14.dp, top = 4.dp)
                                                    .size(34.dp)
                                            )
                                        }
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth(0.9f),
                                        horizontalAlignment = Alignment.Start,
                                    ) {
                                        Text(
                                            text = task.title,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (task.isComplete) Color.Gray else MaterialTheme.colorScheme.onBackground,
                                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None
                                        )
                                        Text(
                                            text = task.description,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (task.isComplete) Color.Gray else MaterialTheme.colorScheme.onBackground,
                                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.deleteTask(task) },
                                        modifier = Modifier.size(width = 90.dp, height = 30.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Delete,
                                            contentDescription = "Delete Category",
                                            tint = MaterialTheme.colorScheme.onBackground
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (showDialog && categoryToDelete != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Delete Category") },
                text = { Text("This category contains tasks. Are you sure you want to delete this category along with all its tasks?") },
                confirmButton = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                val tasksInCategory = searchedTasks.filter { it.categoryId == categoryToDelete!!.id }
                                tasksInCategory.forEach { viewModel.deleteTask(it) }
                                viewModel.deleteCategory(categoryToDelete!!)
                                selectedCategoryId = null
                                showDialog = false
                            },
                            shape = RoundedCornerShape(12),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
                        ) {
                            Text(text = "Yes", color = MaterialTheme.colorScheme.onBackground)
                        }
                        Button(
                            onClick = { showDialog = false },
                            shape = RoundedCornerShape(12),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
                        ) {
                            Text(text = "No", color = MaterialTheme.colorScheme.onBackground)
                        }
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TempPreview() {


    Column(
        Modifier.clickable { }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
//                backgroundColor = Color.Transparent,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Row {
                        Box{
                            IconButton(
                                onClick = {
//                                viewModel.toggleTaskCompletion(task)
                                },
                                modifier = Modifier
                                    .padding(start = 4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.task_round),
                                    contentDescription = "Example Image",
                                    modifier = Modifier.size(32.dp),
//                                tint = if (darkTheme) DarkPrimaryTint else LightPrimaryTint
                                )
                            }
                            if (true) {
                                Image(
                                    painter = painterResource(R.drawable.task_tick),
                                    contentDescription = null,
//                                colorFilter = ColorFilter.tint(if (darkTheme) DarkPrimaryTint else LightPrimaryTint),
                                    modifier = Modifier
                                        .padding(start = 14.dp, top = 4.dp)
                                        .size(34.dp)
                                )
                            }
                        }
                        Column {
                            Text(
//                                modifier = Modifier
//                                    .padding(start = 50.dp),
                                text = "task.title",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
//                            color = if (task.isComplete) Color.Gray else MaterialTheme.colorScheme.onBackground,
//                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 50.dp),
                                text = "task.title",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
//                            color = if (task.isComplete) Color.Gray else MaterialTheme.colorScheme.onBackground,
//                            textDecoration = if (task.isComplete) TextDecoration.LineThrough else TextDecoration.None
                            )
                        }
                        IconButton(
                            onClick = {

                            },
                            modifier = Modifier.size(width = 90.dp, height = 30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete, // Replace with your delete icon resource
                                contentDescription = "Delete Category",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomePagePreview() {
//    Row {
//
//        Button(
//            modifier = Modifier.padding(4.dp),
//            onClick = { /*TODO*/ },
//            shape = RoundedCornerShape(12),
//            colors = ButtonDefaults.buttonColors(Color.Transparent),
//            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//        ) {
//            Text(
//                modifier = Modifier
//                    .padding(3.dp),
//                text = " category.name ",
//                color = MaterialTheme.colorScheme.primary,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.SemiBold,
//            )
//        }
//    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
    IconButton(
        onClick = {

        },
        modifier = Modifier.size(width = 90.dp, height = 30.dp)
    ) {
        Row(
//                        modifier = Modifier.size(width = 200.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Delete",
//                          style = MaterialTheme.typography.h6,
                color = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                imageVector = Icons.Filled.Delete, // Replace with your delete icon resource
                contentDescription = "Delete Category",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
    }
}