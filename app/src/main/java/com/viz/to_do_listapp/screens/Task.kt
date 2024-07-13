package com.viz.to_do_listapp.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.viz.to_do_listapp.Routes
import com.viz.to_do_listapp.Model.Category
import com.viz.to_do_listapp.viewModel.TaskViewModel
import com.viz.to_do_listapp.Model.Task


@Composable
fun AddTask(viewModel: TaskViewModel, navController: NavController, categories: List<Category>) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    var selectedCategoryId by remember { mutableStateOf(-1) }
    var selectedCategoryName by remember { mutableStateOf("All Tasks") }
    var isExpanded by remember { mutableStateOf(false) }
    var showNewCategoryField by remember { mutableStateOf(false) }
    var newCategoryName by remember { mutableStateOf("") }

    val task = Task(title, description, categoryId = selectedCategoryId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = title,
            onValueChange = {
                if (it.length <= 25) {
                    title = it
                } else {
                    Toast.makeText(
                        context,
                        "Title cannot be more than 25 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            placeholder = {
                Text(
                    text = "Add Task",
                    color = Color.Gray,
                    fontWeight = FontWeight(600),
                    fontSize = 24.sp
                )
            },
            textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = Transparent,
                unfocusedContainerColor = Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            placeholder = {
                Text(
                    text = "Add description ?",
                    color = Color.Gray,
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp
                )
            },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = Transparent,
                unfocusedContainerColor = Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(12.dp))

        // Category Drop Down Menu and + Icon
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Add to :",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight(600),
                fontSize = 18.sp
            )
            Box {
                Button(
                    onClick = { isExpanded = !isExpanded },
                    shape = RoundedCornerShape(12),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(1.dp, Color.Transparent),
                ) {
                    Text(
                        text = selectedCategoryName,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Drop Down Arrow",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier.padding(4.dp)
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(text = category.name) },
                            onClick = {
                                selectedCategoryId = category.id
                                selectedCategoryName = category.name
                                isExpanded = false
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

            IconButton(
                onClick = {
                    showNewCategoryField = !showNewCategoryField
                }
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "Add Category",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        // New Category Text Field
        if (showNewCategoryField) {
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = newCategoryName,
                    onValueChange = {
                        if (it.length <= 15) {
                            newCategoryName = it
                        } else {
                            Toast.makeText(
                                context,
                                "Category cannot be more than 15 characters",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    placeholder = {
                        Text(
                            text = "New Category",
                            color = Color.Gray,
                            fontWeight = FontWeight(600),
                            fontSize = 20.sp
                        )
                    },
                    textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                        cursorColor = MaterialTheme.colorScheme.onBackground,
                        focusedContainerColor = Transparent,
                        unfocusedContainerColor = Transparent,
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )
                IconButton(
                    onClick = {
                        if (newCategoryName.isNotBlank()) {
                            viewModel.upsertCategory(Category(name = newCategoryName))
                            selectedCategoryName = newCategoryName
                            selectedCategoryId = categories.last().id + 1
                            showNewCategoryField = false
                        } else {
                            Toast.makeText(
                                context,
                                "Category name cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Add New Category",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

    // Add button
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        CustomFloatingActionButton(
            onClick = {
                if (task.title.isNotBlank()) {
                    viewModel.upsertTask(task)
                    navController.navigate(Routes.App)
                } else navController.popBackStack()
            },
            icon = Icons.Filled.Check
        )
    }
}
