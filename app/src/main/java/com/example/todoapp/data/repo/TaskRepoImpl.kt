package com.example.todoapp.data.repo

import com.example.todoapp.data.local.dao.TodoTaskDao
import com.example.todoapp.data.mapper.toDomain
import com.example.todoapp.data.mapper.toEntity
import com.example.todoapp.domain.model.TodoTaskModel
import com.example.todoapp.domain.repo.TaskRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepoImpl @Inject constructor(
    private val taskDao: TodoTaskDao
) : TaskRepo {

    override fun getAllTasks(): Flow<List<TodoTaskModel>> {
        return taskDao.getAllTasks().map {
            it.map { task ->
                task.toDomain()
            }
        }
    }

    override suspend fun getTaskById(taskId: Int): TodoTaskModel? {
        return taskDao.getTaskById(taskId)?.toDomain()
    }

    override fun getFavouriteTask(): Flow<List<TodoTaskModel>> {
        return taskDao.getFavouriteTask().map { tasks ->
            tasks.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insertTask(task: TodoTaskModel) {
        taskDao.insertTask(task.toEntity())
    }

    override suspend fun updateTask(task: TodoTaskModel) {
        taskDao.updateTask(task.toEntity())
    }

    override suspend fun deleteTask(task: TodoTaskModel) {
        taskDao.deleteTask(task.toEntity())
    }
}