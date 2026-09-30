package com.example.todoapp.data.mapper

import com.example.todoapp.data.local.entity.ToDoTask
import com.example.todoapp.domain.model.TodoTaskModel

fun ToDoTask.toDomain() : TodoTaskModel{
    return TodoTaskModel(
        id = id,
        title = title,
        description = description,
        isFavourite = isFavourite
    )
}

fun TodoTaskModel.toEntity() : ToDoTask{
    return ToDoTask(
        id = id,
        title = title,
        description = description,
        isFavourite = isFavourite
    )
}