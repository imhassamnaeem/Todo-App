package com.example.todoapp.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.presentation.addtodo.TaskAdapter
import com.example.todoapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>
    (FragmentHomeBinding::inflate){
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

    private fun setupRecyclerview(){
        taskAdapter = TaskAdapter(
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
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                viewModel.tasks.collect { task ->
                    taskAdapter.submitList(task)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.homeEvent.collect{event ->
                    when(event){
                        is HomeEvent.OnNavigateToAddToDo ->
                            findNavController().navigate(R.id.action_homeFragment_to_addToDoFragment)
                        is HomeEvent.OnNavigateToFavourite ->
                            findNavController().navigate(R.id.action_homeFragment_to_favouritFragment)
                    }
                }
            }
        }
    }
}
