package com.example.todoapp.domain.repo

import com.example.todoapp.data.local.entity.ToDoTask
import kotlinx.coroutines.flow.Flow

interface TaskRepo {
    fun getAllTasks() : Flow<List<ToDoTask>>
    suspend fun getTaskById(taskId: Int): ToDoTask?
     fun getFavouriteTask(): Flow<List<ToDoTask>>
    suspend fun insertTask(task: ToDoTask)
    suspend fun updateTask(task: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}