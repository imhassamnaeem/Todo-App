package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.core.shared.utils.database_name
import com.example.todoapp.data.local.dao.TodoTaskDao
import com.example.todoapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            database_name
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: AppDatabase): TodoTaskDao {
        return database.todoTaskDao()
    }
}