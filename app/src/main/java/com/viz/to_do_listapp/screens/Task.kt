package com.viz.to_do_listapp.screens

import android.graphics.Outline
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.viz.to_do_listapp.Routes
import com.viz.to_do_listapp.Routes.category
import com.viz.to_do_listapp.roomDB.Category
import com.viz.to_do_listapp.viewModel.TaskViewModel
import com.viz.to_do_listapp.roomDB.Task



//
//@Composable
//fun AddTask(viewModel: TaskViewModel, navController: NavController, categories : List<Category>) {
//
//    var title by remember { mutableStateOf(" ") }
//    var description by remember { mutableStateOf(" ") }
//    val context = LocalContext.current
//
//    var selectedCategoryId by remember { mutableStateOf(1) }
//    var selectedCategoryName by remember { mutableStateOf("Category") }
//    val task = Task(title, description, categoryId = selectedCategoryId)
//    var isExpanded by remember { mutableStateOf(false) }
//
////    var status by remember { mutableStateOf(showDialogBox) }
//
//
//    AlertDialog(
//        onDismissRequest = {  },
//        confirmButton = {
//            Row (modifier = Modifier
//                .fillMaxWidth(),
////                .padding(8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween) {
//
//                Button(
//                    onClick = { navController.navigate(Routes.App) },
//                    shape = RoundedCornerShape(12),
//                    colors = ButtonDefaults.buttonColors(Color.Transparent),
//                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                ) {
//                    Text(text = "Cancel", color = MaterialTheme.colorScheme.onBackground)
//                }
//                Button(
//                    onClick = {
//                        if(task.title.isNotBlank()) {
//                            viewModel.upsertTask(task)
//                            navController.navigate(Routes.App)
//                        } else Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
//                              },
//                    shape = RoundedCornerShape(12),
//                    colors = ButtonDefaults.buttonColors(Color.Transparent),
//                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                ) {
//                    Text(text = "Add", color = MaterialTheme.colorScheme.onBackground)
//                }
//
//            }
//        },
//
//        title = { Text(text = "Create Task", color = MaterialTheme.colorScheme.onBackground)},
//        text = {
//            Column(
//                modifier = Modifier.padding(16.dp),
//            ) {
//                OutlinedTextField(
//                    value = title,
//                    onValueChange = {
//                        if (it.length <= 25) {
//                            title = it
//                        } else {
//                            Toast.makeText(context, "Title cannot be more than 25 characters", Toast.LENGTH_SHORT).show()
//                        } },
//                    label = { Text(text = "Task") },
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
//                        unfocusedTextColor = MaterialTheme.colorScheme.background,
//                        focusedLabelColor = MaterialTheme.colorScheme.onBackground,
//                        focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    singleLine = true
//                )
////                OutlinedTextField(
////                    value = description,
////                    onValueChange = { description = it },
////                    label = { Text(text = "details") },
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(8.dp)
////                )
//
//                Box(
//                    modifier = Modifier.fillMaxWidth(),
////                    contentAlignment = Alignment.TopEnd
//                ) {
//                    Button(
//                        onClick = { isExpanded = !isExpanded },
////                        modifier = Modifier.align(Alignment.End),
//                        shape = RoundedCornerShape(12),
//                        colors = ButtonDefaults.buttonColors(Color.Transparent),
//                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                    ) {
//                        Text(text = selectedCategoryName, color = MaterialTheme.colorScheme.onBackground)
//                    }
//                    DropdownMenu(
//                        modifier = Modifier.align(Alignment.TopEnd),
//                        expanded = isExpanded,
//                        onDismissRequest = { isExpanded = false
////                                       categoryId = selectedCategoryId
//                        },
//                        content = {
//                            categories.forEach { category ->
//                                DropdownMenuItem(
//                                    text = { Text(text = category.name) },
//                                    onClick = {
//                                        selectedCategoryId = category.id
//                                        selectedCategoryName = category.name
//                                        isExpanded = false
//                                    },
//                                    modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.onBackground
//                                        )//, RoundedCornerShape(12))
////                                        .align(Alignment.End)
//                                )
//                            }
//                        },
////                    modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.onBackground)
//                    )
//                }
//            }
//        },
//        containerColor = MaterialTheme.colorScheme.background
//    )
//
//}
//
//@Composable
//fun AddTask(viewModel: TaskViewModel, navController: NavController, categories: List<Category>) {
//    var title by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    var selectedCategoryId by remember { mutableStateOf(categories.firstOrNull()?.id ?: 1) }
//    var selectedCategoryName by remember { mutableStateOf(categories.firstOrNull()?.name ?: "Category") }
//    var isExpanded by remember { mutableStateOf(false) }
//    val context = LocalContext.current
//
//    AlertDialog(
//        onDismissRequest = { },
//        confirmButton = {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Button(
//                    onClick = { navController.navigate(Routes.App) },
//                    shape = RoundedCornerShape(12),
//                    colors = ButtonDefaults.buttonColors(Color.Transparent),
//                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                ) {
//                    Text(text = "Cancel", color = MaterialTheme.colorScheme.onBackground)
//                }
//                Button(
//                    onClick = {
//                        if (title.isNotBlank()) {
//                            val task = Task(title, description, categoryId = selectedCategoryId)
//                            viewModel.upsertTask(task)
//                            navController.navigate(Routes.App)
//                        } else {
//                            Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
//                        }
//                    },
//                    shape = RoundedCornerShape(12),
//                    colors = ButtonDefaults.buttonColors(Color.Transparent),
//                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                ) {
//                    Text(text = "Add", color = MaterialTheme.colorScheme.onBackground)
//                }
//            }
//        },
//        title = { Text(text = "Create Task", color = MaterialTheme.colorScheme.onBackground) },
//        text = {
//            Column(modifier = Modifier.padding(16.dp)) {
//                OutlinedTextField(
//                    value = title,
//                    onValueChange = {
//                        if (it.length <= 30) {
//                            title = it
//                        } else {
//                            Toast.makeText(context, "Title cannot be more than 30 characters", Toast.LENGTH_SHORT).show()
//                        }
//                    },
//                    label = { Text(text = "Task") },
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
//                        unfocusedTextColor = MaterialTheme.colorScheme.background,
//                        focusedLabelColor = MaterialTheme.colorScheme.onBackground,
//                        focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    singleLine = true
//                )
//
//                Box(modifier = Modifier.fillMaxWidth()) {
//                    Button(
//                        onClick = { isExpanded = !isExpanded },
//                        shape = RoundedCornerShape(12),
//                        colors = ButtonDefaults.buttonColors(Color.Transparent),
//                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                    ) {
//                        Text(text = selectedCategoryName, color = MaterialTheme.colorScheme.onBackground)
//                    }
//                    DropdownMenu(
//                        expanded = isExpanded,
//                        onDismissRequest = { isExpanded = false },
//                        modifier = Modifier.align(Alignment.TopEnd)
//                    ) {
//                        categories.forEach { category ->
//                            DropdownMenuItem(
//                                text = { Text(text = category.name) },
//                                onClick = {
//                                    selectedCategoryId = category.id
//                                    selectedCategoryName = category.name
//                                    isExpanded = false
//                                },
//                                modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.onBackground)
//                            )
//                        }
//                    }
//                }
//            }
//        },
//        containerColor = MaterialTheme.colorScheme.background
//    )
//}


//@Preview(showBackground = true)
@Composable
fun AddTask(viewModel: TaskViewModel, navController: NavController, categories : List<Category>) {
    var title by remember { mutableStateOf("") } // don't put " " always put "" as value, try it and placeholder boom!!
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current
//
    var selectedCategoryId by remember { mutableStateOf(1) }
    var selectedCategoryName by remember { mutableStateOf("Category") }
    val task = Task(title, description, categoryId = selectedCategoryId)
    var isExpanded by remember { mutableStateOf(false) }

//    var status by remember { mutableStateOf(showDialogBox) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = title,
            onValueChange ={
                if (it.length <= 25) {
                    title = it
                } else {
                    Toast.makeText(
                        context,
                        "Title cannot be more than 25 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } ,
            placeholder = { Text(text = "Add Task", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight(600), fontSize = 24.sp) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground, // Underline Color
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = Transparent,  // Background color
                unfocusedContainerColor = Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(4.dp))

        TextField(
            value = description,
            onValueChange ={
                description = it
            } ,
            placeholder = { Text(text = "Add description (optional)", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight(600), fontSize = 24.sp) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground, // Underline Color
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = Transparent,  // Background color
                unfocusedContainerColor = Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(4.dp))

        Column(
            modifier = Modifier.padding(16.dp),
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
//                    contentAlignment = Alignment.TopEnd
            ) {
                Button(
                    onClick = { isExpanded = !isExpanded },
//                        modifier = Modifier.align(Alignment.End),
                    shape = RoundedCornerShape(12),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
                ) {
                    Text(
                        text = selectedCategoryName,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                DropdownMenu(
                    modifier = Modifier.align(Alignment.TopEnd),
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
//                                       categoryId = selectedCategoryId
                    },
                    content = {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(text = category.name) },
                                onClick = {
                                    selectedCategoryId = category.id
                                    selectedCategoryName = category.name
                                    isExpanded = false
                                },
                                modifier = Modifier.border(
                                    1.dp, MaterialTheme.colorScheme.onBackground
                                )//, RoundedCornerShape(12))
//                                        .align(Alignment.End)
                            )
                        }
                    },
//                    modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.onBackground)
                )


//        containerColor = MaterialTheme.colorScheme.background
            }

        }


        // Add and Cancel button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { navController.navigate(Routes.App) },
                shape = RoundedCornerShape(12),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
            ) {
                Text(text = "Cancel", color = MaterialTheme.colorScheme.onBackground)
            }
            Button(
                onClick = {
                    if (task.title.isNotBlank()) {
                        viewModel.upsertTask(task)
                        navController.navigate(Routes.App)
                    } else Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT)
                        .show()
                },
                shape = RoundedCornerShape(12),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
            ) {
                Text(text = "Add", color = MaterialTheme.colorScheme.onBackground)
            }

        }
    }
}


// NewTask but plceholder problem
//@Composable
//fun AddTask(viewModel: TaskViewModel, navController: NavController, categories : List<Category>) {
//
//    var title by remember { mutableStateOf(" ") }
//    var description by remember { mutableStateOf(" ") }
//    val context = LocalContext.current
//
//    var selectedCategoryId by remember { mutableStateOf(1) }
//    var selectedCategoryName by remember { mutableStateOf("Category") }
//    val task = Task(title, description, categoryId = selectedCategoryId)
//    var isExpanded by remember { mutableStateOf(false) }
//
////    var status by remember { mutableStateOf(showDialogBox) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//
//        TextField(
//            value = title,
//            onValueChange ={
//                if (it.length <= 25) {
//                title = it
//                } else {
//                    Toast.makeText(
//                        context,
//                        "Title cannot be more than 25 characters",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                           } ,
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(min = 56.dp)
//                .padding(horizontal = 16.dp),
//               //.background(Color.Transparent),
//            placeholder = { Text(text = " Add Task ", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight(600), fontSize = 21.sp) },
////            label = { Text(text = "Task") },
//
////            colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.onBackground),
//            colors = TextFieldDefaults.colors(
//                focusedTextColor = MaterialTheme.colorScheme.onBackground,
//                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
////                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
//                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground, // Underline Color
//                cursorColor = MaterialTheme.colorScheme.onBackground,
//                focusedContainerColor = Transparent,  // Background color
//                unfocusedContainerColor = Transparent,
//            ),
//
////            focusedIndicatorColor = Color.Transparent, // Hide the focused indicator
////            unfocusedIndicatorColor = Color.Transparent,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            singleLine = true,
//        )
//        Spacer(modifier = Modifier.padding(6.dp))
//
//        TextField(
//            value = description,
//            onValueChange = { description = it },
//            placeholder = { Text(text = " Add Description (optional) ", fontWeight = FontWeight(600), fontSize = 21.sp) },
////            modifier = Modifier
////                .height(100.dp)
////                .fillMaxWidth()
////                .heightIn(min = 56.dp)
////                .padding(horizontal = 16.dp),
//            colors = TextFieldDefaults.colors(
//                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground, // Underline Color
//                cursorColor = MaterialTheme.colorScheme.onBackground,
//                focusedContainerColor = Transparent,  // Background color
//                unfocusedContainerColor = Transparent,
//                focusedPlaceholderColor = Color.Red,
//                unfocusedPlaceholderColor = Color.Yellow
//            )
//        )
//
//
//        Column(
//            modifier = Modifier.padding(16.dp),
//        ) {
//
//            Box(
//                modifier = Modifier.fillMaxWidth(),
////                    contentAlignment = Alignment.TopEnd
//            ) {
//                Button(
//                    onClick = { isExpanded = !isExpanded },
////                        modifier = Modifier.align(Alignment.End),
//                    shape = RoundedCornerShape(12),
//                    colors = ButtonDefaults.buttonColors(Color.Transparent),
//                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//                ) {
//                    Text(
//                        text = selectedCategoryName,
//                        color = MaterialTheme.colorScheme.onBackground
//                    )
//                }
//                DropdownMenu(
//                    modifier = Modifier.align(Alignment.TopEnd),
//                    expanded = isExpanded,
//                    onDismissRequest = {
//                        isExpanded = false
////                                       categoryId = selectedCategoryId
//                    },
//                    content = {
//                        categories.forEach { category ->
//                            DropdownMenuItem(
//                                text = { Text(text = category.name) },
//                                onClick = {
//                                    selectedCategoryId = category.id
//                                    selectedCategoryName = category.name
//                                    isExpanded = false
//                                },
//                                modifier = Modifier.border(
//                                    1.dp, MaterialTheme.colorScheme.onBackground
//                                )//, RoundedCornerShape(12))
////                                        .align(Alignment.End)
//                            )
//                        }
//                    },
////                    modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.onBackground)
//                )
//
//
////        containerColor = MaterialTheme.colorScheme.background
//            }
//
//        }
//
//
//        // Add and Cancel button
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//
//            Button(
//                onClick = { navController.navigate(Routes.App) },
//                shape = RoundedCornerShape(12),
//                colors = ButtonDefaults.buttonColors(Color.Transparent),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//            ) {
//                Text(text = "Cancel", color = MaterialTheme.colorScheme.onBackground)
//            }
//            Button(
//                onClick = {
//                    if (task.title.isNotBlank()) {
//                        viewModel.upsertTask(task)
//                        navController.navigate(Routes.App)
//                    } else Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT)
//                        .show()
//                },
//                shape = RoundedCornerShape(12),
//                colors = ButtonDefaults.buttonColors(Color.Transparent),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
//            ) {
//                Text(text = "Add", color = MaterialTheme.colorScheme.onBackground)
//            }
//
//        }
//    }
//}

