package com.viz.to_do_listapp.viewModel

import com.viz.to_do_listapp.roomDB.Task
import com.viz.to_do_listapp.roomDB.TaskDatabase

class Repository(private val db : TaskDatabase) {
    suspend fun upsertTask(task: Task) {
        db.dao.upsertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        db.dao.deleteTask(task)
    }

//    suspend fun getTaskById(id: Int) = db.dao.getTaskById(id)

    fun getAllTasks() = db.dao.getAllTasks()
}