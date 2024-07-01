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
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>

    @Upsert
    suspend fun upsertCategory(category: Category)
    @Delete
    suspend fun deleteCategory(category: Category)
    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<Category>>


}