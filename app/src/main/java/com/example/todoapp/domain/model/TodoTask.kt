package com.example.todoapp.domain.model

data class TodoTaskModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isFavourite: Boolean = false
)