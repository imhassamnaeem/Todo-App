package com.example.todoapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.entity.ToDoTask
import com.example.todoapp.domain.repo.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TaskRepo
) : ViewModel() {

    private val _homeEvent = MutableSharedFlow<HomeEvent>()
    val homeEvent = _homeEvent.asSharedFlow()

    val tasks = repository.getAllTasks()

    fun navigateToAddTodo() {
        viewModelScope.launch {
            _homeEvent.emit(HomeEvent.OnNavigateToAddToDo)
        }
    }

    fun navigateToFavourite() {
        viewModelScope.launch {
            _homeEvent.emit(HomeEvent.OnNavigateToFavourite)
        }
    }

    fun deleteTask(task: ToDoTask) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun addToFavourite(task: ToDoTask) {
        val updatedTask = task.copy(isFavourite = !task.isFavourite)
        viewModelScope.launch {
            repository.updateTask(updatedTask)
        }
    }
}

sealed class HomeEvent {
    data object OnNavigateToAddToDo : HomeEvent()
    data object OnNavigateToFavourite : HomeEvent()
}