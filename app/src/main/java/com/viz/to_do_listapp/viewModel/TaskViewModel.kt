package com.viz.to_do_listapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.viz.to_do_listapp.Model.Category
import com.viz.to_do_listapp.Model.Task
import com.viz.to_do_listapp.screens.FilterOption
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: Repository) : ViewModel() {
    private val _filterOption = MutableLiveData<FilterOption>(FilterOption.Alphabet)
    val filterOption: LiveData<FilterOption> get() = _filterOption

    fun getTasks() = repository.getAllTasks().asLiveData(viewModelScope.coroutineContext)
    fun getCategories() = repository.getAllCategories().asLiveData(viewModelScope.coroutineContext)
    fun getTasksByCategory(categoryId: Int) = repository.getTasksByCategory(categoryId).asLiveData(viewModelScope.coroutineContext)

    fun applyFilter(filterOption: FilterOption) {
        _filterOption.value = filterOption
    }

    fun upsertTask(task: Task) {
        viewModelScope.launch {
            repository.upsertTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun toggleTaskCompletion(task: Task) {
        val updatedTask = task.copy(isComplete = !task.isComplete)
        upsertTask(updatedTask)
    }

    fun upsertCategory(category: Category) {
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
