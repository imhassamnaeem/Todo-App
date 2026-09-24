package com.example.todoapp.data.repo

import com.example.todoapp.data.local.dao.TodoTaskDao
import com.example.todoapp.data.local.entity.ToDoTask
import com.example.todoapp.domain.repo.TaskRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepoImpl @Inject constructor(
    private val taskDao: TodoTaskDao
) : TaskRepo {

    override fun getAllTasks(): Flow<List<ToDoTask>> {
        return taskDao.getAllTasks()
    }

    override suspend fun getTaskById(taskId: Int): ToDoTask? {
        return taskDao.getTaskById(taskId)
    }

    override fun getFavouriteTask(): Flow<List<ToDoTask>> {
        return taskDao.getFavouriteTask()
    }

    override suspend fun insertTask(task: ToDoTask) {
        taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: ToDoTask) {
        taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: ToDoTask) {
        taskDao.deleteTask(task)
    }
}