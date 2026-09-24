package com.example.todoapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.local.entity.ToDoTask
import com.example.todoapp.databinding.ItemTasksBinding

class TaskAdapter(
    private val onFavouriteClick: (ToDoTask) -> Unit,
    private val onEditClick: (ToDoTask) -> Unit,
    private val onDeleteClick: (ToDoTask) -> Unit
) : ListAdapter<ToDoTask, TaskAdapter.TaskViewHolder>(TodoDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {

        val binding = ItemTasksBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val task = getItem(position)
        holder.binding.apply {

            tvTaskName.text = task.title
            tvTaskDetail.text = task.description
            btnFavourite.setImageResource(
                if (task.isFavourite) {
                    R.drawable.favourite
                } else {
                    R.drawable.unfavourite
                }
            )

            btnFavourite.setOnClickListener {
                onFavouriteClick(task)
            }

            btnEdit.setOnClickListener {
                onEditClick(task)
            }

            delete.setOnClickListener {
                onDeleteClick(task)
            }
        }
    }

    class TaskViewHolder(val binding: ItemTasksBinding) : RecyclerView.ViewHolder(binding.root)
    class TodoDiffCallBack : DiffUtil.ItemCallback<ToDoTask>() {
        override fun areItemsTheSame(
            oldItem: ToDoTask,
            newItem: ToDoTask
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ToDoTask,
            newItem: ToDoTask
        ): Boolean {
            return oldItem == newItem
        }

    }
}