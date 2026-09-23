package com.example.todoapp.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentFavouritBinding
import com.example.todoapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouritBinding, FavouriteViewModel>(
    FragmentFavouritBinding::inflate
) {
    override val viewModel: FavouriteViewModel by viewModels()
    private lateinit var favouriteAdapter: FavouriteAdapter
    override fun setUpViews() {
      setUpFavouriteRecyclerView()
    }
    fun setUpFavouriteRecyclerView(){
        favouriteAdapter = FavouriteAdapter(
            onFavouriteClick = {task ->
                viewModel.removeFromFavourite(task)
            }
        )
        binding.favouriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter =favouriteAdapter
        }
    }

    override fun observeData() {
      viewLifecycleOwner.lifecycleScope.launch {
          viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
          {
              viewModel.favouriteTasks.collect { tasks ->
                  favouriteAdapter.submitList(tasks)
              }
          }
      }
    }
}
