package com.viz.to_do_listapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.viz.to_do_listapp.roomDB.Category
import com.viz.to_do_listapp.roomDB.Task
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: Repository): ViewModel() {
    fun getTasks() = repository.getAllTasks().asLiveData(viewModelScope.coroutineContext)

    fun upsertTask(task: Task){
        viewModelScope.launch {
                repository.upsertTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun toggleTaskCompletion(task: Task) {
        val updatedTask = task.copy(isComplete = !task.isComplete)
        upsertTask(updatedTask)
    }

    fun getCategories() = repository.getAllCategories().asLiveData(viewModelScope.coroutineContext)

    fun upsertCategory(category: Category){
        viewModelScope.launch {
            repository.upsertCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
    }
}