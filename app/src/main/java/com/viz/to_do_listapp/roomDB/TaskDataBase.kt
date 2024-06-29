package com.viz.to_do_listapp.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract val dao: RoomDao

//    companion object {
//        @Volatile
//        private var INSTANCE: TaskDatabase? = null

//        fun getDatabase(context: Context): TaskDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TaskDatabase::class.java,
//                    "task_database"
//                )
//                    .addMigrations(MIGRATION_1_2)
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }

//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Task ADD COLUMN isComplete INTEGER NOT NULL DEFAULT 0")
//            }
//        }
//    }
}


//@Database(entities = [Task::class], version = 1)
//abstract class TaskDataBase: RoomDatabase() {
//    abstract val doa : RoomDao
//}