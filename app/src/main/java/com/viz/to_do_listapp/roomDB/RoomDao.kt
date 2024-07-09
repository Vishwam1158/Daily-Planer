package com.viz.to_do_listapp.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.viz.to_do_listapp.Model.Category
import com.viz.to_do_listapp.Model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Upsert
    suspend fun upsertTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)

    @Upsert
    suspend fun upsertCategory(category: Category)
    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM task ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Task>>
    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<Category>>
    @Query("SELECT * FROM task WHERE categoryId = :categoryId")
    fun getTasksByCategory(categoryId: Int): Flow<List<Task>>
    @Query("SELECT * FROM task WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Task>>
    @Query("SELECT * FROM task ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByPriority(): LiveData<List<Task>>
    @Query("SELECT * FROM task WHERE isComplete = 'true' ")
    fun getCompleteTasks(): LiveData<List<Task>>
    @Query("SELECT * FROM task WHERE isComplete = 'false' ")
    fun getIncompleteTasks(): LiveData<List<Task>>

//    @Query("SELECT * FROM task ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
//    fun sortByLowPriority(): LiveData<List<Task>>
}