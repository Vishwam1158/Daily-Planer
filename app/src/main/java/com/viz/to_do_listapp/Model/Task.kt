package com.viz.to_do_listapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    val title: String,
    val description: String,
    val isComplete: Boolean = false,
    val categoryId: Int,
    var priority: Priority = Priority.MEDIUM,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)