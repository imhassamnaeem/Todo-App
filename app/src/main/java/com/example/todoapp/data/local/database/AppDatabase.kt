package com.example.todoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.local.dao.TodoTaskDao
import com.example.todoapp.data.local.entity.ToDoTask

@Database(
    entities = [ToDoTask::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun todoTaskDao(): TodoTaskDao

}