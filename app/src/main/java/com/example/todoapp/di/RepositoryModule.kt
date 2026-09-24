package com.example.todoapp.di

import com.example.todoapp.data.local.dao.TodoTaskDao
import com.example.todoapp.data.repo.TaskRepoImpl
import com.example.todoapp.domain.repo.TaskRepo
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
    fun provideTaskRepository(taskDao: TodoTaskDao): TaskRepo {
        return TaskRepoImpl(taskDao)
    }
}