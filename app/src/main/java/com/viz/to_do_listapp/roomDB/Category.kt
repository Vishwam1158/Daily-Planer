package com.viz.to_do_listapp.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
