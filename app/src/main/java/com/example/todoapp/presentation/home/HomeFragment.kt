package com.example.todoapp.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var taskAdapter: TaskAdapter
    override fun setUpViews() {
        binding.apply {
            floatingActionEditButton.setOnClickListener {
                viewModel.navigateToAddTodo()
            }
            btnFav.setOnClickListener {
                viewModel.navigateToFavourite()
            }
        }
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        taskAdapter = TaskAdapter(
            onFavouriteClick = { task ->
                viewModel.addToFavourite(task)
            },

            onEditClick = { task ->
                val action = HomeFragmentDirections
                    .actionHomeFragmentToAddToDoFragment(task.id)
                findNavController().navigate(action)
            },
            onDeleteClick = { task ->
                viewModel.deleteTask(task)
            }
        )
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }
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

