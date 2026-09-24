package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.data.local.dao.TodoTaskDao
import com.example.todoapp.data.local.database.AppDatabase
import com.example.todoapp.core.shared.utils.database_name
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            database_name
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database : AppDatabase) : TodoTaskDao{
        return database.todoTaskDao()
    }
}