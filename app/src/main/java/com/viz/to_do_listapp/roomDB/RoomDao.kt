package com.viz.to_do_listapp.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
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

    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>
    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<Category>>
    @Query("SELECT * FROM task WHERE categoryId = :categoryId")
    fun getTasksByCategory(categoryId: Int): Flow<List<Task>>


}