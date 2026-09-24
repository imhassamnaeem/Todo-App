package com.example.todoapp.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.entity.ToDoTask
import com.example.todoapp.domain.repo.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: TaskRepo
) : ViewModel() {
    val favouriteTasks = repository.getFavouriteTask()
    fun removeFromFavourite(task: ToDoTask){
        val updatedTask = task.copy(isFavourite = false)
        viewModelScope.launch {
            repository.updateTask(updatedTask)
        }
    }

}