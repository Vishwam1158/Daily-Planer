package com.viz.to_do_listapp

import android.os.Bundle
import com.newrelic.agent.android.NewRelic
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.viz.to_do_listapp.roomDB.TaskDatabase
import com.viz.to_do_listapp.ui.theme.ToDOlistAppTheme
import com.viz.to_do_listapp.viewModel.Repository
import com.viz.to_do_listapp.viewModel.TaskViewModel

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "todolist2.db"
        ).build()
    }
    private val viewModel by viewModels<TaskViewModel>( // In the viewModel we pass repository so here we use factory producer
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        NewRelic.withApplicationToken(
            "AAd89327b71b784e1d20f265c8562218d9226a5f74-NRMA"
        ).start(this.applicationContext)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var darkTheme by remember { mutableStateOf(false) }
            ToDOlistAppTheme(darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(viewModel = viewModel, darkTheme = darkTheme, onThemeUpdated = { darkTheme = !darkTheme })
                }
            }

        }
    }
}


