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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.compose.rememberNavController
import com.viz.to_do_listapp.Routes
import com.viz.to_do_listapp.Routes.category
import com.viz.to_do_listapp.roomDB.Category
import com.viz.to_do_listapp.viewModel.TaskViewModel
import com.viz.to_do_listapp.roomDB.Task


//MAin Add Task
@Composable
fun AddTask(viewModel: TaskViewModel, navController: NavController, categories : List<Category>) {
    var title by remember { mutableStateOf("") } // don't put " " always put "" as value, try it and placeholder boom!!
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    var selectedCategoryId by remember { mutableStateOf(1) }
    var selectedCategoryName by remember { mutableStateOf("Category") }
    val task = Task(title, description, categoryId = selectedCategoryId)
    var isExpanded by remember { mutableStateOf(false) }

//    var status by remember { mutableStateOf(showDialogBox) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 36.dp, bottom = 24.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
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
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(600),
                    fontSize = 24.sp
                )
            },
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
                    text = "Add description (optional)",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(600),
                    fontSize = 24.sp
                )
            },
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
                .background(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Button(
                onClick = { isExpanded = !isExpanded },
                shape = RoundedCornerShape(12),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, Color.Transparent)
            ) {
                Text(
                    text = selectedCategoryName,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Drop Down Arrow"
                )
            }

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                },
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(text = category.name) },
                        onClick = {
                            selectedCategoryId = category.id
                            selectedCategoryName = category.name
                            isExpanded = false
                        },
                        modifier = Modifier
                            .border(
                                1.dp, MaterialTheme.colorScheme.onBackground
                            )
                            .align(Alignment.End)
                    )
                }
            }

            IconButton(
                onClick = {
                    navController.navigate(Routes.category)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "Add Category"
                )
            }
        }
    }

    // Add button
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ) {
            CustomFloatingActionButton(
                navController = navController,
                onClick = {
                    if (task.title.isNotBlank()) {
                        viewModel.upsertTask(task)
                        navController.navigate(Routes.App)
                    } else Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT)
                        .show()
                },
                icon = Icons.Filled.Check
            )
    }
}
