package com.example.todoapp.presentation.addtodo

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
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

    private var currentTask: ToDoTask? = null

    override fun setUpViews() {
        binding.apply {
            btnSave.setOnClickListener {
                val title = evTitle.text.toString()
                val description = evDiscription.text.toString()
                if (title.isEmpty()) {
                    evTitle.error = getString(R.string.title_required)
                    return@setOnClickListener
                }
                if (description.isEmpty()) {
                    evDiscription.error = getString(R.string.description_required)
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

    override fun observeData() {
        loadAndCollectOnStarted()
        {
            viewModel.taskEvent.collect { event ->
                when (event) {
                    is TaskEvent.NavigateToHome ->
                        findNavController().popBackStack()

                    is TaskEvent.NavigateToUpdate ->
                        event.task?.let { task ->
                            currentTask = task
                            binding.apply {
                                tvTodo.text = getString(R.string.update_task)
                                evTitle.setText(task.title)
                                evDiscription.setText(task.description)
                                btnSave.text = getString(R.string.update)
                            }
                        }
                }
            }
        }
    }
}