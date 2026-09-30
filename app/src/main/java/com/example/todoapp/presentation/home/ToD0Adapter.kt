package com.example.todoapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTasksBinding
import com.example.todoapp.domain.model.TodoTaskModel

class TaskAdapter(
    private val onFavouriteClick: (TodoTaskModel) -> Unit,
    private val onEditClick: (TodoTaskModel) -> Unit,
    private val onDeleteClick: (TodoTaskModel) -> Unit
) : ListAdapter<TodoTaskModel, TaskAdapter.TaskViewHolder>(TodoDiffCallBack()) {

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

    class TaskViewHolder(
        val binding: ItemTasksBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class TodoDiffCallBack : DiffUtil.ItemCallback<TodoTaskModel>() {
        override fun areItemsTheSame(
            oldItem: TodoTaskModel,
            newItem: TodoTaskModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TodoTaskModel,
            newItem: TodoTaskModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}