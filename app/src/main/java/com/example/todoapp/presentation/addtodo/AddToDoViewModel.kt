package com.example.todoapp.presentation.addtodo

import androidx.lifecycle.SavedStateHandle
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
class AddToDoViewModel @Inject constructor(
    private val repository: TaskRepo,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _taskEvent = MutableSharedFlow<TaskEvent>()
    val taskEvent = _taskEvent.asSharedFlow()

    val taskId = savedStateHandle.get<Int>("taskId")?: -1

    init {
        if (taskId != -1) {
            getTaskById(taskId)
        }
    }
    fun insertTask(task: ToDoTask) {
        viewModelScope.launch {
            repository.insertTask(task)
            _taskEvent.emit(TaskEvent.NavigateToHome)
        }
    }
    fun updateTask(task: ToDoTask){
        viewModelScope.launch {
            repository.updateTask(task)
            _taskEvent.emit(TaskEvent.NavigateToHome)
        }
    }
    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
        val task = repository.getTaskById(taskId)
            _taskEvent.emit(TaskEvent.NavigateToUpdate(task))
        }
    }
}

sealed class TaskEvent {
    data object NavigateToHome : TaskEvent()
    data class NavigateToUpdate(val task: ToDoTask?) : TaskEvent()
}