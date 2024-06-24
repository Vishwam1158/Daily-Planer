package com.viz.to_do_listapp.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.viz.to_do_listapp.Routes
import com.viz.to_do_listapp.viewModel.TaskViewModel
import com.viz.to_do_listapp.roomDB.Task



//
@Composable
fun Task(viewModel: TaskViewModel, navController: NavController) {

    var title by remember { mutableStateOf(" ") }
    var description by remember { mutableStateOf(" ") }
    val task = Task(title, description)
    val context = LocalContext.current

//    var status by remember { mutableStateOf(showDialogBox) }


    AlertDialog(
        onDismissRequest = {  },
        confirmButton = {
            Row (modifier = Modifier
                .fillMaxWidth(),
//                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {

                Button(
                    onClick = { navController.navigate(Routes.App) },
                    shape = RoundedCornerShape(12),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Cancel", color = Color.Black)
                }
                Button(
                    onClick = {
                        if(task.title.isNotBlank() || task.title.length <=20) {
                            viewModel.upsertTask(task)
                            navController.navigate(Routes.App)
                        } else Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
                              },
                    shape = RoundedCornerShape(12),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Add", color = Color.Black)
                }

            }
        },

        title = { Text(text = "Create Task")},
        text = {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = title,
//                    onValueChange = {
//                        if(task.title.length <= 20)  title = it else Toast.makeText(navController.context, "Title cannot be more than 20 characters", Toast.LENGTH_SHORT).show() },
                    onValueChange = {
                        if (it.length <= 20) {
                            title = it
                        } else {
                            Toast.makeText(context, "Title cannot be more than 20 characters", Toast.LENGTH_SHORT).show()
                        } },
                    label = { Text(text = "title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "details") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    )

}



@Preview(showBackground = true)
@Composable
private fun TaskPreview() {

}