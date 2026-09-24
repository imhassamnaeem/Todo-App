package com.example.todoapp.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.core.shared.base.BaseFragment
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.core.shared.extensions.loadAndCollectOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModels()

    private val taskAdapter =  TaskAdapter(
        onFavouriteClick = {task ->
            viewModel.addToFavourite(task)
        },
        onEditClick = {task ->
            val action = HomeFragmentDirections
                .actionHomeFragmentToAddToDoFragment(task.id)
            findNavController().navigate(action)
        },
        onDeleteClick = {task ->
            viewModel.deleteTask(task)
        }
    )

    override fun setUpViews() {
        binding.apply {
            floatingActionEditButton.setOnClickListener {
                viewModel.navigateToAddTodo()
            }
            btnFav.setOnClickListener {
                viewModel.navigateToFavourite()
            }
        }
        binding.tasksRecyclerView.adapter = taskAdapter
    }

    override fun observeData() {
        loadAndCollectOnStarted()
        {
            viewModel.tasks.collect { task ->
                taskAdapter.submitList(task)
            }
        }

        loadAndCollectOnStarted {
            viewModel.homeEvent.collect { event ->
                when (event) {
                    is HomeEvent.OnNavigateToAddToDo ->
                        findNavController().navigate(R.id.action_homeFragment_to_addToDoFragment)

                    is HomeEvent.OnNavigateToFavourite ->
                        findNavController().navigate(R.id.action_homeFragment_to_favouritFragment)
                }
            }
        }
    }
}