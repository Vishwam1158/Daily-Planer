package com.viz.to_do_listapp.viewModel

import com.viz.to_do_listapp.roomDB.Category
import com.viz.to_do_listapp.roomDB.Task
import com.viz.to_do_listapp.roomDB.TaskDatabase

class Repository(private val db : TaskDatabase) {
    suspend fun upsertTask(task: Task) {
        db.dao.upsertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        db.dao.deleteTask(task)
    }

    fun getAllTasks() = db.dao.getAllTasks()

    // for category
    suspend fun upsertCategory(category: Category) {
        db.dao.upsertCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        db.dao.deleteCategory(category)
    }

    fun getAllCategories() = db.dao.getAllCategories()
}