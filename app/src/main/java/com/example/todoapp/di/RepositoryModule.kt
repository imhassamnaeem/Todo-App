package com.example.todoapp.di

import com.example.todoapp.data.local.dao.TodoTaskDao
import com.example.todoapp.data.remote.repository.TaskRepoImplementation
import com.example.todoapp.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule {
    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TodoTaskDao): TaskRepository {
        return TaskRepoImplementation(taskDao)
    }

}