package com.viz.to_do_listapp.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    val title: String,
    val description: String,
    val isComplete: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)