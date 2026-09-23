package com.example.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.local.entity.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTaskDao {
    @Query("SELECT * FROM  tasks ORDER BY ID ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): ToDoTask?

    @Query("SELECT * FROM tasks WHERE isFavourite = 1 ORDER BY id ASC")
    fun getFavouriteTask(): Flow<List<ToDoTask>>

    @Insert
    suspend fun insertTask(task: ToDoTask)

    @Update
    suspend fun updateTask(task: ToDoTask)

    @Delete
    suspend fun deleteTask(task: ToDoTask)
}