package com.example.todoapp.presentation.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.local.entity.ToDoTask
import com.example.todoapp.databinding.ItemFavouriteBinding

class FavouriteAdapter(private val onFavouriteClick: (ToDoTask)-> Unit) : ListAdapter<ToDoTask, FavouriteAdapter.FavouriteViewHolder>(FavouriteDiffCallBack())
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteViewHolder {
val binding = ItemFavouriteBinding.inflate(
    LayoutInflater.from(parent.context),
    parent,
    false
)
        return FavouriteViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: FavouriteViewHolder,
        position: Int
    ) {
val task = getItem(position)
    holder.binding.apply {
        tvTaskName.text = task.title
        tvTaskDetail.text= task.description
        btnFavourite.setImageResource(
            R.drawable.favourite
        )
        btnFavourite.setOnClickListener {
            onFavouriteClick(task)
        }
    }
    }

    class FavouriteViewHolder(val binding: ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root)
    class FavouriteDiffCallBack: DiffUtil.ItemCallback<ToDoTask>(){
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