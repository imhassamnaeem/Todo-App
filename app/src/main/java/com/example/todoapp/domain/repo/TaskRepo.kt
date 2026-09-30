package com.example.todoapp.domain.repo

import com.example.todoapp.domain.model.TodoTaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepo {
    fun getAllTasks(): Flow<List<TodoTaskModel>>
    suspend fun getTaskById(taskId: Int): TodoTaskModel?
    fun getFavouriteTask(): Flow<List<TodoTaskModel>>
    suspend fun insertTask(task: TodoTaskModel)
    suspend fun updateTask(task: TodoTaskModel)
    suspend fun deleteTask(task: TodoTaskModel)
}