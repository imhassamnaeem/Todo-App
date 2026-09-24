package com.example.todoapp.presentation.addtodo

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.core.shared.base.BaseFragment
import com.example.todoapp.core.shared.extensions.loadAndCollectOnStarted
import com.example.todoapp.data.local.entity.ToDoTask
import com.example.todoapp.databinding.FragmentAddToDoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment : BaseFragment<FragmentAddToDoBinding, AddToDoViewModel>(
    FragmentAddToDoBinding::inflate
) {
    override val viewModel: AddToDoViewModel by viewModels()
    private val args: AddToDoFragmentArgs by navArgs()
    private var currentTask: ToDoTask? = null
    override fun setUpViews() {
        if (args.taskId != -1) {
            viewModel.getTaskById(args.taskId)
        }
        binding.apply {
            btnSave.setOnClickListener {
                val title = evTitle.text.toString()
                val description = evDiscription.text.toString()
                if (title.isEmpty()) {
                    evTitle.error = "Title is required"
                    return@setOnClickListener
                }
                if (description.isEmpty()) {
                    evDiscription.error = "Description is required"
                    return@setOnClickListener
                }
                if (currentTask == null) {
                    val task = ToDoTask(
                        title = title,
                        description = description
                    )
                    viewModel.insertTask(task)
                } else {
                    val updateTask = currentTask!!.copy(
                        title = title,
                        description = description
                    )
                    viewModel.updateTask(updateTask)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        loadAndCollectOnStarted()
        {
            viewModel.taskEvent.collect { event ->
                when (event) {
                    is TaskEvent.NavigateToHome ->
                        findNavController().popBackStack()

                    is TaskEvent.TaskInserted ->
                        event.task?.let { task ->
                            currentTask = task
                            binding.apply {
                                tvTodo.text = "Update Task"
                                evTitle.setText(task.title)
                                evDiscription.setText(task.description)
                                btnSave.text = "Update"
                            }
                        }
                }
            }
        }
    }
}
